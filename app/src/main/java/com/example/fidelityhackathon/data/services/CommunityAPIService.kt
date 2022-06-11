package com.example.fidelityhackathon.data.services

import com.example.fidelityhackathon.data.models.CheckUserInCommunityResponse
import com.example.fidelityhackathon.data.models.CommunityMembersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface CommunityAPIService {
    @Headers("Content-Type: application/json")
    @GET("/community/check-user-in-community")
    suspend fun checkUserInCommunity(@Header("x-auth-token") token: String): Response<CheckUserInCommunityResponse>

    @Headers("Content-Type: application/json")
    @POST("/community/add-user-in-community")
    suspend fun addUserInCommunity(@Header("x-auth-token") token: String): Response<CommunityMembersResponse>

    @Headers("Content-Type: application/json")
    @GET("/community/show-community-members")
    suspend fun showCommunityMembers(@Header("x-auth-token") token: String): Response<CommunityMembersResponse>
}