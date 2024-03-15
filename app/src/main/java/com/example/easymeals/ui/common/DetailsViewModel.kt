package com.example.easymeals.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easymeals.data.Meal
import com.example.easymeals.repo.Repository
import com.example.medicalapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _mutableLiveData = MutableLiveData<Resource<Meal>>()
    val mutableLiveData get() = _mutableLiveData


    fun getRandomMeal(mealId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getMealDetails(mealId)
                if (response.meals.isNotEmpty()) {
                    _mutableLiveData.postValue(Resource.Success(response.meals[0]))
                } else {
                    _mutableLiveData.postValue(Resource.Error(response.toString()))
                }

            } catch (e: Exception) {
                _mutableLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }


}