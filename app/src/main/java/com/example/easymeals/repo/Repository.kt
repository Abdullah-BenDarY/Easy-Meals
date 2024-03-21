package com.example.easymeals.repo


import com.example.easymeals.network.ApiCalls
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

    suspend fun getAllCategories() =
        api.getAllCategories()

    suspend fun getMealsByCategory(category: String )=
        api.getMealsByCategory(category)
}

