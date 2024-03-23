package com.example.easymeals.base

import android.app.Application
import com.example.easymeals.repo.MyDataBase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        MyDataBase.getInstance(this)

    }
}