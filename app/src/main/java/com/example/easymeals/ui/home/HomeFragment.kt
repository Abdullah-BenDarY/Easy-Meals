package com.example.easymeals.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.easymeals.adapters.AdapterPopularMeals
import com.example.easymeals.base.BaseFragment
import com.example.easymeals.databinding.FragmentHomeBinding
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.SEA_FOOD
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val adapterPopularMeals = AdapterPopularMeals()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getRandomMeal()
        homeViewModel.getPupularMeals(SEA_FOOD)
        observe()
        onClicks()
        binding.rvPopItems.layoutManager = LinearLayoutManager(requireContext())
    }

    private var ide: Int? = null
    override fun onClicks() {
        binding.apply {
            imgRandomMeal.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(ide!!))
            }
        }
        adapterPopularMeals.setOnClick {
            findNavController()
                .navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                )
        }
    }

    override fun observe() {
        homeViewModel.randomMealLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let {
                        Glide.with(binding.root.context)
                            .load(it.strMealThumb)
                            .into(binding.imgRandomMeal)
                        ide = it.idMeal.toInt()
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
                    it.data?.let {
                        adapterPopularMeals.mealList = it
                        binding.rvPopItems.adapter = adapterPopularMeals
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

}




