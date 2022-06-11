package com.example.fidelityhackathon.ui.authentication

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fidelityhackathon.R
import com.example.fidelityhackathon.data.models.AuthenticationTokens
import com.example.fidelityhackathon.data.models.LoginAndSignUpPayLoad
import com.example.fidelityhackathon.databinding.AuthenticationFragmentBinding
import com.example.fidelityhackathon.presentation.viewmodels.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.Response

@AndroidEntryPoint
class AuthenticationFragment: Fragment() {
    private lateinit var binding: AuthenticationFragmentBinding

    private val authenticationViewModel: AuthenticationViewModel by viewModels()

    private val INTERVAL = 2000 // 2 Second
    private val handler: Handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthenticationFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.continueButton.setOnClickListener {
            val checkedID = binding.radioGroup.checkedRadioButtonId

            val isLoginSelected = checkedID == R.id.login_button
            val isSignUpSelected = checkedID == R.id.sign_up_button

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if(email.isEmpty()){
                showEmailErrorText("Email is required")
                return@setOnClickListener
            }

            val payLoad = LoginAndSignUpPayLoad(email, password)

            if(isLoginSelected){
                viewLifecycleOwner.lifecycleScope.launch {
                    authenticationViewModel.doLogin(payLoad).let {
                        if(!it.isSuccessful){
                            showEmailErrorText("Wrong email")
                            return@let
                        }
                        proceedWithSuccessfulAuthentication(it)
                    }
                }
            }

            if(isSignUpSelected){
                viewLifecycleOwner.lifecycleScope.launch {
                    authenticationViewModel.doSignUp(payLoad).let {
                        if(!it.isSuccessful){
                            if(it.code() == 400)
                                showEmailErrorText("Email already registered")
                            else
                                showEmailErrorText("Wrong email")
                            return@let
                        }
                        proceedWithSuccessfulAuthentication(it)
                    }
                }
            }
        }
    }

    private suspend fun proceedWithSuccessfulAuthentication(it: Response<AuthenticationTokens>) {
        authenticationViewModel.saveTokens(
            it.body()?.accessToken ?: "",
            it.body()?.refreshToken ?: ""
        )
        val action = AuthenticationFragmentDirections.actionAuthenticationFragmentToHomePageFragment(it.body()?.accessToken?:"")
        findNavController().navigate(action)
    }

    private fun showEmailErrorText(errorMsg: String) {
        binding.errorMsg.text = errorMsg
        binding.errorMsg.visibility = View.VISIBLE

        val runnable = Runnable {
            binding.errorMsg.visibility = View.GONE
        }
        handler.postAtTime(runnable, System.currentTimeMillis()+INTERVAL)
        handler.postDelayed(runnable, INTERVAL.toLong())
    }
}