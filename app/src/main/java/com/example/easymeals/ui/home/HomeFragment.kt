package com.example.easymeals.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.easymeals.adapters.AdapterCategories
import com.example.easymeals.adapters.AdapterPopularMeals
import com.example.easymeals.base.BaseFragment
import com.example.easymeals.databinding.FragmentHomeBinding
import com.example.easymeals.pojo.Category
import com.example.easymeals.pojo.Meal
import com.example.easymeals.pojo.PMeal
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val adapterPopularMeals = AdapterPopularMeals()
    private val adapterCategories = AdapterCategories()
    private val categoryList = arrayListOf(
        "Beef", "Chicken", "Dessert", "Lamb", "Miscellaneous",
        "Pasta", "Pork", "Seafood", "Side", "Starter",
        "Vegan", "Vegetarian", "Breakfast", "Goat")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        homeViewModel.getRandomMeal()
        homeViewModel.getPupularMeals(categoryList.random())
        homeViewModel.getAllCategories()
        observe()
        onClicks()
    }

    private var ide: Int? = null
    override fun onClicks() {
        binding.apply {
            imgRandomMeal.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(ide!!))
            }
                imgRandomMeal.setOnLongClickListener{
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToMealBottomSheetFragment(ide!!))
                 true
                }
        }

        adapterPopularMeals.setOnLongClick {
            findNavController()
                .navigate(
                    HomeFragmentDirections.actionHomeFragmentToMealBottomSheetFragment(it)
                )
        }
        adapterPopularMeals.setOnClick {
            findNavController()
                .navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                )
        }
        adapterCategories.setOnClick {
            findNavController()
                .navigate(
                    HomeFragmentDirections.actionHomeFragmentToMealsFragment(it)
                )
        }
    }

    override fun observe() {
        homeViewModel.randomMealLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    finishLoading()
                    it.data?.let {
                        setRandomMealView(it)

                    }
                }

                is Resource.Error -> {
                    it.message?.let { message ->
                        showToast(message)

                    }
                }
            }
        }

        homeViewModel.popularMealsLiveData.observe(viewLifecycleOwner) { it ->
            when (it) {
                is Resource.Success -> {
                    finishLoading()
                    it.data?.let {
                        setPopularMealView(it)
                    }
                }

                is Resource.Error -> {
                    it.message?.let { message ->
                        showToast(message)
                    }
                }
            }
        }

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
            imgRandomMeal.visibility = View.INVISIBLE
            rvPopItems.visibility = View.INVISIBLE
        }
    }

    override fun finishLoading() {
        super.finishLoading()
        binding.apply {
            progresBar.visibility = View.INVISIBLE
            rvCategory.visibility = View.VISIBLE
            imgRandomMeal.visibility = View.VISIBLE
            rvPopItems.visibility = View.VISIBLE
        }
    }

    private fun setRandomMealView(response: Meal) {
        Glide.with(binding.root.context)
            .load(response.strMealThumb)
            .into(binding.imgRandomMeal)
        ide = response.idMeal.toInt()
    }

    private fun setPopularMealView(response: List<PMeal>) {
        adapterPopularMeals.mealList = response
        binding.rvPopItems.adapter = adapterPopularMeals
    }

    private fun setAllCategoriesView(response: List<Category>) {
        adapterCategories.categoryList = response
        binding.rvCategory.adapter = adapterCategories
    }
}




