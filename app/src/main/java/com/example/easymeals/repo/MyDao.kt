package com.example.easymeals.repo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.easymeals.pojo.Meal

@Dao
interface MyDao {

//    @Insert
//    fun insertFavorite(meal: Meal)
//
//    @Update
//    fun updateFavorite(meal:Meal)
//
//    @Query("SELECT * FROM mealInfo order by idMeal asc")
//    fun getAllSavedMeals():LiveData<List<Meal>>
//
//    @Query("SELECT * FROM mealInfo WHERE idMeal =:id")
//    fun getMealById(id:String):Meal
//
//    @Query("DELETE FROM mealInfo WHERE idMeal =:id")
//    fun deleteMealById(id:String)
//
//    @Delete
//    fun deleteMeal(meal:Meal)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert (meal:Meal)

    @Delete
    suspend fun deleteMeal (meal : Meal)

    @Query("SELECT * FROM mealInfo")
    fun getAllMeals ():LiveData<List<Meal>>
}