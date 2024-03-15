package com.example.easymeals.network

import com.example.easymeals.data.ModelRandomMeal
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiCalls {
    @GET("random.php")
    suspend fun getRandomMeal(): ModelRandomMeal
    @GET("lookup.php?")
    suspend fun deatMealDetails(
        @Query("i") mealId: String
    ): ModelRandomMeal


}