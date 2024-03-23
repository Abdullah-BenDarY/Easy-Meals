package com.example.easymeals.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.easymeals.adapters.AdapterCategories
import com.example.easymeals.adapters.AdapterFCategories
import com.example.easymeals.base.BaseFragment
import com.example.easymeals.databinding.FragmentCategoriesBinding
import com.example.easymeals.databinding.FragmentDetailsBinding
import com.example.easymeals.databinding.FragmentFavorietsBinding
import com.example.easymeals.pojo.Category
import com.example.easymeals.pojo.Meal
import com.example.easymeals.pojo.PMeal
import com.example.easymeals.ui.home.HomeFragmentDirections
import com.example.easymeals.ui.home.HomeViewModel
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment :BaseFragment<FragmentCategoriesBinding>(FragmentCategoriesBinding::inflate) {
    private val homeViewModel: HomeViewModel by viewModels()
    private val adapterFCategories = AdapterFCategories()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getAllCategories()
        showLoading()
        observe()
        onClicks()
    }

    override fun onClicks() {
        super.onClicks()
        binding.apply {
            adapterFCategories.setOnClick {
                findNavController()
                    .navigate(
            CategoriesFragmentDirections.actionCategoriesFragmentToMealsFragment(it)
                    )
            }
        }
    }

    override fun observe() {
        super.observe()
        homeViewModel.allCategoriesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    finishLoading()
                    it.data?.let {
                        setAllCategoriesView(it)
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
        super.showLoading()
        binding.apply {
            progresBar.visibility = View.VISIBLE
            rvCategory.visibility = View.INVISIBLE

        }
    }

    override fun finishLoading() {
        super.finishLoading()
        binding.apply {
            progresBar.visibility = View.INVISIBLE
            rvCategory.visibility = View.VISIBLE

        }
    }

    private fun setAllCategoriesView(response: List<Category>) {
        adapterFCategories.categoryList = response
        binding.rvCategory.adapter = adapterFCategories
    }


}