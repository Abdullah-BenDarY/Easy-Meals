package com.example.easymeals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.easymeals.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class  MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavController()
    }

    private fun setupNavController(){
        val navController = findNavController(R.id.fragmentView)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btmNav)
        bottomNavigationView.setupWithNavController(navController)

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}