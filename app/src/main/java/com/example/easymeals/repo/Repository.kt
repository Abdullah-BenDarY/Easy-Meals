package com.example.easymeals.repo


import com.example.easymeals.network.ApiCalls
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val api: ApiCalls){
//    private val originalList: ModelRandomMeal? = null

    suspend fun getRandom() =
      api.getRandomMeal()
    suspend fun getMealDetails(mealId : Int) =
      api.getMealDetails(mealId)

}

