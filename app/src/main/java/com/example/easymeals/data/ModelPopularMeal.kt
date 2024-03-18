package com.example.easymeals.data

data class ModelPopularMeal(
    val pMeals: List<PMeal>
)

data class PMeal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)