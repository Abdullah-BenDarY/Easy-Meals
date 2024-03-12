package com.example.easymeals.ui.home

import android.os.Bundle
import android.util.Log
import android.view.Display.Mode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.easymeals.data.Meal
import com.example.easymeals.data.ModelRandomMeal
import com.example.easymeals.databinding.FragmentHomeBinding
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.internal.toImmutableList

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        homeViewModel.getRandomMeal()
        observe()
//        onClicks()
    }

    private fun onClicks() {
        binding.apply {

        }
    }

    private fun observe() {

        homeViewModel.mutableLiveData.observe(viewLifecycleOwner) { meal ->
            when (meal) {
                is Resource.Success -> {
                    meal.data?.let {meal ->
                        showToast(meal.idMeal)
                            Glide.with(binding.root.context)
                                .load(meal.strMealThumb)
                                .into(binding.imgRandomMeal)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}




