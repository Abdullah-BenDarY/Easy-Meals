package com.example.easymeals.network

import com.example.easymeals.pojo.ModelAllCategories
import com.example.easymeals.pojo.ModelPopularMeal
import com.example.easymeals.pojo.ModelRandomMeal
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCalls {
    @GET("random.php")
    suspend fun getRandomMeal(): ModelRandomMeal

    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") mealId: Int
    ): ModelRandomMeal

    @GET("filter.php")
    suspend fun getPopularMeals(
        @Query("c") category: String
    ): ModelPopularMeal

    @GET("categories.php")
    suspend fun getAllCategories()
            : ModelAllCategories

    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ): ModelPopularMeal

}