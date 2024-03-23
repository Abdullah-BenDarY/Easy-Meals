package com.example.easymeals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
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
    private fun setupNavController() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btmNav)
        val navController = findNavController(R.id.fragmentView)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    if (navController.currentDestination?.id != R.id.homeFragment) {
                        navController.navigate(R.id.homeFragment)
                        return@setOnItemSelectedListener true
                    }
                }
                R.id.categoriesFragment -> {
                    navController.navigate(R.id.categoriesFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.favorietsFragment -> {
                    navController.navigate(R.id.favorietsFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}