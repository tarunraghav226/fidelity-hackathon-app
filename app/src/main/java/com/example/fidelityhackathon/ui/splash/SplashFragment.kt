package com.example.fidelityhackathon.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fidelityhackathon.databinding.SplashFragmentBinding
import com.example.fidelityhackathon.presentation.viewmodels.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment: Fragment() {
    private lateinit var binding: SplashFragmentBinding

    private val authenticationViewModel: AuthenticationViewModel by viewModels()

    private val INTERVAL = 2000 // 2 Second
    private val handler: Handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SplashFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val runnable = Runnable {
                viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                    val authToken = authenticationViewModel.getAuthenticationToken()
                    val action = if(authToken.isEmpty()){
                        SplashFragmentDirections.actionSplashFragmentToAuthenticationFragment()
                    } else {
                        SplashFragmentDirections.actionSplashFragmentToHomePageFragment(authToken)
                    }
                    findNavController().navigate(action)
                }
            }
        handler.postAtTime(runnable, System.currentTimeMillis()+INTERVAL)
        handler.postDelayed(runnable, INTERVAL.toLong())
    }
}