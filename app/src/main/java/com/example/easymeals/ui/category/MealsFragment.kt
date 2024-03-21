package com.example.easymeals.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.easymeals.adapters.AdapterMealsList
import com.example.easymeals.base.BaseFragment
import com.example.easymeals.databinding.FragmentMealsBinding
import com.example.easymeals.pojo.PMeal
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealsFragment : BaseFragment<FragmentMealsBinding>(FragmentMealsBinding::inflate) {
    private val categoryViewModel : CategoryViewModel by viewModels()
    private val adapterMealsList = AdapterMealsList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        val categoryName = MealsFragmentArgs.fromBundle(requireArguments()).categoryName
        categoryViewModel.getCategoryMealsList(categoryName)
        showLoading()
        onClicks()
        observe()
    }

    override fun onClicks() {
        super.onClicks()
        binding.apply {
            adapterMealsList.setOnClick {
                findNavController()
                    .navigate(
                        MealsFragmentDirections.actionMealsFragmentToDetailsFragment(it)
                    )
            }
        }
    }

    override fun observe() {
        super.observe()
        categoryViewModel.categoryListLiveData.observe(viewLifecycleOwner) {it ->
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
        super.showLoading()
        binding.apply {
            progresBar.visibility = View.VISIBLE
            rvMeals.visibility = View.INVISIBLE
            tvCategoryName.visibility = View.INVISIBLE
        }
    }

    override fun finishLoading() {
        super.finishLoading()
        binding.apply {
            progresBar.visibility = View.INVISIBLE
            rvMeals.visibility = View.VISIBLE
            tvCategoryName.visibility = View.VISIBLE
        }
    }

    private fun setContentInViews(response: List<PMeal>) {
        adapterMealsList.mealList = response
        binding.rvMeals.adapter = adapterMealsList
        val categoryName = MealsFragmentArgs.fromBundle(requireArguments()).categoryName
        val mealsCounter = categoryName +" : "+ response.size.toString()
        binding.tvCategoryName.text = mealsCounter
    }
}