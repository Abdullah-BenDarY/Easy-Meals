package com.example.easymeals.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easymeals.pojo.Meal
import com.example.easymeals.repo.Repository
import com.example.medicalapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject constructor(private val repository: Repository ) : ViewModel() {
    private val _searchMealsLiveData = MutableLiveData<Resource<List<Meal>?>>()
    val searchMealsLiveData get() = _searchMealsLiveData


    fun searchMeals(name : String) {

        viewModelScope.launch (IO){
            try {
                val response = repository.searshByName(name)
                if (response.meals.isNotEmpty()) {
                    _searchMealsLiveData.postValue(Resource.Success(response.meals))
                }else {
                    _searchMealsLiveData.postValue(Resource.Error(response.toString()))
                }
            } catch (e: Exception) {
                _searchMealsLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }
}