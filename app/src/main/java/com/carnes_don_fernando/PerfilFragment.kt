package com.carnes_don_fernando

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.carnes_don_fernando.databinding.FragmentPerfilBinding
import com.carnes_don_fernando.ui.home.HomeViewModel

class PerfilFragment : Fragment() {
    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentPerfilBinding.inflate(inflater,container,false)

        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }


}