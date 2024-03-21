package com.example.easymeals.ui.common

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.easymeals.R
import com.example.easymeals.base.BaseFragment
import com.example.easymeals.pojo.Meal
import com.example.easymeals.databinding.FragmentDetailsBinding
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val detailsViewModel: DetailsViewModel by viewModels()
    private lateinit var uriVideo :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myId = DetailsFragmentArgs.fromBundle(requireArguments()).id
        detailsViewModel.getMealsDetails(myId)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        onClicks()
        observe()
    }
    override fun onClicks() {
        binding.apply {
            btnVideo.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW , Uri.parse(uriVideo))
                startActivity(intent)
            }
        }

    }
    override fun observe() {
        super.observe()
        detailsViewModel.mutableLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    finishLoading()
                    it.data?.let {
                        setContentInViews(it)
                        uriVideo = it.strYoutube

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
            btnAddToFav.visibility = View.GONE
            btnVideo.visibility = View.INVISIBLE
            tvAreaName.visibility = View.INVISIBLE
            tvCategoryName.visibility = View.INVISIBLE
            tvInstrutionsDetails.visibility = View.INVISIBLE
        }
    }
    override fun finishLoading() {
        binding.apply {
            progresBar.visibility = View.INVISIBLE
            btnAddToFav.visibility = View.VISIBLE
            btnVideo.visibility = View.VISIBLE
            tvAreaName.visibility = View.VISIBLE
            tvCategoryName.visibility = View.VISIBLE
            tvInstrutionsDetails.visibility = View.VISIBLE
        }
    }
    private fun setContentInViews(meal: Meal) {
        binding.apply {
            binding.apply {
                Glide.with(binding.root.context)
                    .load(meal.strMealThumb)
                    .into(binding.imgMeal)
                collapsingToolBar.title = meal.strMeal
                collapsingToolBar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
                collapsingToolBar.setExpandedTitleColor(resources.getColor(R.color.white))
                tvAreaName.text = meal.strArea
                tvCategoryName.text = meal.strCategory
                tvInstrutionsDetails.text = meal.strInstructions
            }
        }
    }

    // TODO    save // unSave to faviriets fun


}