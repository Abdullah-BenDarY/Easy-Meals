package com.example.easymeals.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.easymeals.base.BaseFragment
import com.example.easymeals.databinding.FragmentCategoriesBinding
import com.example.easymeals.databinding.FragmentDetailsBinding
import com.example.easymeals.databinding.FragmentFavorietsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment :BaseFragment<FragmentCategoriesBinding>(FragmentCategoriesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}