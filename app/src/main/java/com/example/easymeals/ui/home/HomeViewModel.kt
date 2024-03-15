package com.example.easymeals.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easymeals.data.Meal
import com.example.easymeals.repo.Repository
import com.example.medicalapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private var _mutableLiveData = MutableLiveData<Resource<Meal>>()
    val mutableLiveData get() = _mutableLiveData


        fun getRandomMeal() {
        viewModelScope.launch ( IO ){
            try {
                val response = repository.getRandom()
                if (response.meals.isNotEmpty()) {
                    _mutableLiveData.postValue(Resource.Success(response.meals[0]))
                }else {
                    _mutableLiveData.postValue(Resource.Error(response.toString()))
                }

            } catch (e: Exception) {
            _mutableLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }


}