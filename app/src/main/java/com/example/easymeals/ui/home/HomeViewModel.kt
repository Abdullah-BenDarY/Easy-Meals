package com.example.easymeals.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easymeals.data.Meal
import com.example.easymeals.data.ModelRandomMeal
import com.example.easymeals.network.ApiCalls
import com.example.easymeals.repo.Repository
import com.example.medicalapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    private val _mutableLiveData = MutableLiveData<Resource<Meal>>()
//private val _mutableLiveData = MutableLiveData<ModelRandomMeal?>()

    val mutableLiveData get() = _mutableLiveData


        fun getRandomMeal() {
        viewModelScope.launch ( IO ){
            try {
                val response = repository.getRandom()
                if (response.meals.isNotEmpty()) {
                    _mutableLiveData.postValue(Resource.Success(response.meals.get(0)))
                }else {
                    _mutableLiveData.postValue(Resource.Error(response.toString()))
                }

            } catch (e: Exception) {
            _mutableLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }


}