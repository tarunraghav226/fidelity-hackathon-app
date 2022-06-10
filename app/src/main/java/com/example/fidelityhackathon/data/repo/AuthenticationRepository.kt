package com.example.fidelityhackathon.data.repo

import com.example.fidelityhackathon.data.models.LoginAndSignUpPayLoad
import com.example.fidelityhackathon.data.services.AuthenticationAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(private val authenticationAPIService: AuthenticationAPIService){
    suspend fun doSignUp(payload: LoginAndSignUpPayLoad) =
        withContext(Dispatchers.IO) { authenticationAPIService.signUP(payload) }

    suspend fun doLogin(payload: LoginAndSignUpPayLoad) =
        withContext(Dispatchers.IO) {authenticationAPIService.login(payload)}
}