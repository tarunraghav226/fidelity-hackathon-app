package com.example.fidelityhackathon.ui.guidelines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fidelityhackathon.databinding.CarbonFootprintGuidelinesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuidelinesFragment: Fragment() {
    private lateinit var binding: CarbonFootprintGuidelinesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CarbonFootprintGuidelinesBinding.inflate(inflater)
        return binding.root
    }
}