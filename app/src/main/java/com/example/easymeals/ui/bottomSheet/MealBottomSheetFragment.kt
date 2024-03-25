package com.example.easymeals.ui.bottomSheet
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.easymeals.R
import com.example.easymeals.base.BaseBottomSheet
import com.example.easymeals.databinding.FragmentMealBottomSheetBinding
import com.example.easymeals.pojo.Meal
import com.example.easymeals.ui.common.DetailsViewModel
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealBottomSheetFragment :
BaseBottomSheet<FragmentMealBottomSheetBinding>(FragmentMealBottomSheetBinding::inflate){

    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myId = MealBottomSheetFragmentArgs.fromBundle(requireArguments()).id
        detailsViewModel.getMealsDetails(myId)
        showLoading()
        onClicks()
        observe()
    }

    private var ide: Int? = null
    override fun onClicks() {
        binding.apply {

            tvReadMore.setOnClickListener {
                findNavController().navigate(
                    MealBottomSheetFragmentDirections.actionMealBottomSheetFragmentToDetailsFragment(ide!!))
            }
        }
    }

    override fun observe() {
        detailsViewModel.detailsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    finishLoading()
                    it.data?.let {
                        setContentInViews(it)

                    }
                }
                is Resource.Error -> {
                    it.message?.let { message ->
                        showToast(message)
                    }
                }
            }
        }
    }

    override fun showLoading() {
        binding.apply {
            progresBar.visibility = View.VISIBLE
            imgBtmSheet.visibility = View.INVISIBLE
            tvCategory.visibility = View.INVISIBLE
            tvArea.visibility = View.INVISIBLE
            tvMealName.visibility = View.INVISIBLE
            tvReadMore.visibility = View.INVISIBLE
        }
    }
    override fun finishLoading() {
        binding.apply {
            progresBar.visibility = View.INVISIBLE
            imgBtmSheet.visibility = View.VISIBLE
            tvCategory.visibility = View.VISIBLE
            tvArea.visibility = View.VISIBLE
            tvMealName.visibility = View.VISIBLE
            tvReadMore.visibility = View.VISIBLE
        }
    }
    private fun setContentInViews(meal: Meal) {
        binding.apply {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(meal.strMealThumb)
                    .into(binding.imgBtmSheet)
                tvCategory.text = meal.strCategory
                tvArea.text = meal.strArea
                tvMealName.text = meal.strMeal
                ide = meal.idMeal.toInt()


            }
        }
    }
}