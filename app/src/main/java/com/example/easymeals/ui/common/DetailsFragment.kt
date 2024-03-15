package com.example.easymeals.ui.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.easymeals.base.BaseFragment
import com.example.easymeals.data.Meal
import com.example.easymeals.databinding.FragmentDetailsBinding
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast


class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myId = DetailsFragmentArgs.fromBundle(requireArguments()).id
        detailsViewModel.getRandomMeal(myId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        onClicks()
        observe()
    }
    override fun onClicks() {


    }
    override fun observe() {
        super.observe()
        detailsViewModel.mutableLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    finishLoading()
                    it.data?.let {
                        setTextsInViews(it)
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
    private fun showLoading() {
        binding.apply {
            progresBar.visibility = View.VISIBLE
            btnAddToFav.visibility = View.GONE
            btnVideo.visibility = View.INVISIBLE
            tvAreaName.visibility = View.INVISIBLE
            tvCategoryName.visibility = View.INVISIBLE
            tvInstrutionsDetails.visibility = View.INVISIBLE
        }
    }
    private fun finishLoading() {
        binding.apply {
            progresBar.visibility = View.INVISIBLE
            btnAddToFav.visibility = View.VISIBLE
            btnVideo.visibility = View.VISIBLE
            tvAreaName.visibility = View.VISIBLE
            tvCategoryName.visibility = View.VISIBLE
            tvInstrutionsDetails.visibility = View.VISIBLE
        }
    }
    private fun setTextsInViews(meal: Meal) {
//        this.dtMeal = meal
//         val ytUrl = meal.strYoutube
        binding.apply {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(meal.strMealThumb)
                    .into(binding.imgMeal)
                collapsingToolBar.title = meal.strMeal
                tvAreaName.text = meal.strArea
                tvCategoryName.text = meal.strCategory
                tvInstrutionsDetails.text = meal.strInstructions
//              btnVideo. = meal.strYoutube
            }
        }
    }

    // todo    save to faviriets fun // un save // intent to youtube fun


}