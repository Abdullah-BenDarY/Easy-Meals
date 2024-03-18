package com.example.easymeals.data

data class ModelPopularMeal(
    val meals: List<PMeal>
)

data class PMeal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

