package com.example.easymeals.ui.common

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.easymeals.adapters.AdapterMealSearch
import com.example.easymeals.base.BaseFragment
import com.example.easymeals.databinding.FragmentSearchBinding
import com.example.easymeals.pojo.Meal
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val searchViewModel : SearchViewModel by viewModels()
    private val adapterMealsList = AdapterMealSearch()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        onClicks()
        observe()
    }

    override fun onClicks() {
        super.onClicks()
        binding.apply {
            var searchJob : Job? = null
            etSearchBox.doAfterTextChanged {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    delay(500)
                    searchFun()
                }
            }
            btnSearch.setOnClickListener{
                searchFun()
            }
        }
        adapterMealsList.setOnClick {
            findNavController()
                .navigate(
                    SearchFragmentDirections.actionSearchFragmentToDetailsFragment(it))
        }
    }

    override fun observe() {
        super.observe()
        searchViewModel.searchMealsLiveData.observe(viewLifecycleOwner){
            when(it){
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

    private fun searchFun(){
        val searchQuery = binding.etSearchBox.text.toString()
        if(searchQuery.isNotEmpty()){
            searchViewModel.searchMeals(searchQuery)
        }
    }

    override fun showLoading() {
        super.showLoading()
        binding.apply {
            progresBar.visibility = View.VISIBLE
            rvMeals.visibility = View.INVISIBLE
        }
    }

    override fun finishLoading() {
        super.finishLoading()
        binding.apply {
            progresBar.visibility = View.INVISIBLE
            rvMeals.visibility = View.VISIBLE
        }
    }

    private fun setContentInViews(response: List<Meal>) {
        adapterMealsList.mealList = response
        binding.rvMeals.adapter = adapterMealsList
    }
}