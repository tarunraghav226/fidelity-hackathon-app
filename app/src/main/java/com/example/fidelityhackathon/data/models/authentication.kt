package com.example.fidelityhackathon.data.models

import com.google.gson.annotations.SerializedName

data class LoginAndSignUpPayLoad (
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class AuthenticationTokens(
    @SerializedName("access_token") val accessToken: String? = "",
    @SerializedName("refresh_token") val refreshToken: String? = ""
)

data class CheckUserInCommunityResponse(
    @SerializedName("status") val status: Int? = 0,
    @SerializedName("is_in_community") val isInCommunity: Boolean? = false
)

data class CommunityMembersResponse(
    @SerializedName("status") val status: Int? = 0,
    @SerializedName("members") val members: List<Members>? = listOf()
)

data class Members(
    @SerializedName("email") val email: String = ""
)