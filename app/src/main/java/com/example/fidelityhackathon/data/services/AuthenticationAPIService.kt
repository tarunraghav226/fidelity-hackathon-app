package com.example.fidelityhackathon.data.services

import com.example.fidelityhackathon.data.models.AuthenticationTokens
import com.example.fidelityhackathon.data.models.LoginAndSignUpPayLoad
import retrofit2.Response
import retrofit2.http.*

interface AuthenticationAPIService {
    @Headers("Content-Type: application/json")
    @POST("/users/signup")
    suspend fun signUP(@Body payload: LoginAndSignUpPayLoad): Response<AuthenticationTokens>

    @Headers("Content-Type: application/json")
    @POST("/users/login")
    suspend fun login(@Body payload: LoginAndSignUpPayLoad): Response<AuthenticationTokens>

}