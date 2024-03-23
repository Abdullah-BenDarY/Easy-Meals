package com.example.easymeals.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.easymeals.pojo.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class MyDataBase : RoomDatabase() {
    abstract fun mealsDao() : MyDao

    companion object {
        @Volatile
         var INSTANCE: MyDataBase? = null

        @Synchronized
        fun getInstance(context: Context): MyDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = databaseBuilder(
                    context.applicationContext,
                    MyDataBase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}