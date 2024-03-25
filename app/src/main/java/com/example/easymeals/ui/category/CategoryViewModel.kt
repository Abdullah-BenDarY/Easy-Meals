package com.example.easymeals.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easymeals.pojo.Category
import com.example.easymeals.pojo.PMeal
import com.example.easymeals.repo.Repository
import com.example.medicalapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: Repository) : ViewModel()  {
    private var _categoryMealsListLiveData = MutableLiveData<Resource<List<PMeal>?>>()
    val categoryListLiveData get() = _categoryMealsListLiveData
    private var saveStateCategory: Resource<List<PMeal>?>?=null


    fun getCategoryMealsList(category : String) {
        saveStateCategory?.let {
            categoryListLiveData.postValue(it)
            return
        }
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repository.getMealsByCategory(category)
                if (response.meals.isNotEmpty()) {
                    _categoryMealsListLiveData.postValue(Resource.Success(response.meals))
                    saveStateCategory = Resource.Success(response.meals)
                }else {
                    _categoryMealsListLiveData.postValue(Resource.Error(response.toString()))
                }
            } catch (e: Exception) {
                _categoryMealsListLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }


}