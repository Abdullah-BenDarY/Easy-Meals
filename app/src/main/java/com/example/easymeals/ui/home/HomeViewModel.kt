package com.example.easymeals.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easymeals.data.Meal
import com.example.easymeals.data.PMeal
import com.example.easymeals.repo.Repository
import com.example.medicalapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private var _randomMealLiveData = MutableLiveData<Resource<Meal>>()
    val randomMealLiveData get() = _randomMealLiveData

    private var _popularMealsLiveData = MutableLiveData<Resource<List<PMeal>?>>()
    val popularMealsLiveData get() = _popularMealsLiveData

        fun getRandomMeal() {
        viewModelScope.launch ( IO ){
            try {
                val response = repository.getRandom()
                if (response.meals.isNotEmpty()) {
                    _randomMealLiveData.postValue(Resource.Success(response.meals[0]))
                }else {
                    _randomMealLiveData.postValue(Resource.Error(response.toString()))
                }

            } catch (e: Exception) {
            _randomMealLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }

    fun getPupularMeals(category : String) {
        viewModelScope.launch ( IO ){
            try {
                val response = repository.getPopularMeal(category)
                if (response.meals.isNotEmpty()) {
                    _popularMealsLiveData.postValue(Resource.Success(response.meals))
                }else {
                    _popularMealsLiveData.postValue(Resource.Error(response.toString()))
                }
            } catch (e: Exception) {
                _popularMealsLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }


}