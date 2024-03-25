package com.example.easymeals.ui.favoriets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.easymeals.base.BaseFragment
import com.example.easymeals.databinding.FragmentCategoriesBinding
import com.example.easymeals.databinding.FragmentFavorietsBinding
import com.example.easymeals.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavorietsFragment : BaseFragment<FragmentFavorietsBinding>(FragmentFavorietsBinding::inflate)