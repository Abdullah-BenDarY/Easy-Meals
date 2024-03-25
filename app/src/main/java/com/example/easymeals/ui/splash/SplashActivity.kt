package com.example.easymeals.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.easymeals.base.MainActivity
import com.example.easymeals.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
 class SplashActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash)
            delayingIntent()
        }

    private fun delayingIntent() =
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 7380)

    }