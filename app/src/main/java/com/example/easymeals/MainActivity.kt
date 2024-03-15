package com.example.easymeals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.easymeals.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  MainActivity : AppCompatActivity() {

     private var binding:ActivityMainBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupNavController()
    }

    private fun setupNavController(){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btmNav)
        val navController = findNavController(R.id.fragmentView)
        bottomNavigationView.setupWithNavController(navController)
//        NavigationUI.setupWithNavController(bottomNavigationView,navController)


    }



    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}