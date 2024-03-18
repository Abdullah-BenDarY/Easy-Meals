package com.example.easymeals.repo


import com.example.easymeals.data.PMeal
import com.example.easymeals.network.ApiCalls
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val api: ApiCalls){

    suspend fun getRandom() =
      api.getRandomMeal()

    suspend fun getMealDetails(mealId : Int) =
      api.getMealDetails(mealId)

    suspend fun getPopularMeal(category: String )=
        api.getPopularMeals(category)

}

