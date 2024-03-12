package com.example.easymeals.network

import com.example.easymeals.data.ModelRandomMeal
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiCalls {
    @GET("random.php")
    suspend fun getRandomMeal(): ModelRandomMeal
}