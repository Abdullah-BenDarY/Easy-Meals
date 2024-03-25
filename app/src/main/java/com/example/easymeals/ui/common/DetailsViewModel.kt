package com.example.easymeals.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easymeals.pojo.Meal
import com.example.easymeals.repo.Repository
import com.example.medicalapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _detailsLiveData = MutableLiveData<Resource<Meal>>()
    val detailsLiveData get() = _detailsLiveData
    private var saveStateCategory: Resource<Meal>?=null

    fun getMealsDetails(mealId: Int) {
        saveStateCategory?.let {
            detailsLiveData.postValue(it)
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getMealDetails(mealId)
                if (response.meals.isNotEmpty()) {
                    _detailsLiveData.postValue(Resource.Success(response.meals[0]))
                    saveStateCategory = Resource.Success(response.meals[0])

                } else {
                    _detailsLiveData.postValue(Resource.Error(response.toString()))
                }

            } catch (e: Exception) {
                _detailsLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }
}
