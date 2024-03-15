package com.example.easymeals.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.easymeals.base.BaseFragment
import com.example.easymeals.databinding.FragmentHomeBinding
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getRandomMeal()
        observe()
        onClicks()
    }

    override fun onClicks() {
        binding.apply {
            imgRandomMeal.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it.id.toString())
                )
            }
        }
    }

    override fun observe() {
        homeViewModel.mutableLiveData.observe(viewLifecycleOwner) { meal ->
            when (meal) {
                is Resource.Success -> {
                    meal.data?.let { meal ->
                        Glide.with(binding.root.context)
                            .load(meal.strMealThumb)
                            .into(binding.imgRandomMeal)
                        binding.randomMealName.text = meal.strMeal
                    }
                }

                is Resource.Error -> {
                    meal.message?.let { message ->
                        showToast(message)

                    }
                }
            }
        }

    }


}




