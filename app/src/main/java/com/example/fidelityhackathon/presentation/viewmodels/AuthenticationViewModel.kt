package com.example.fidelityhackathon.presentation.viewmodels

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fidelityhackathon.data.models.LoginAndSignUpPayLoad
import com.example.fidelityhackathon.data.repo.AuthenticationRepository
import com.example.fidelityhackathon.storage.DataStoreInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val dataStoreManager: DataStoreInterface
) : ViewModel(){
    suspend fun doSignUp(payLoad: LoginAndSignUpPayLoad) =
        authenticationRepository.doSignUp(payLoad)

    suspend fun doLogin(payLoad: LoginAndSignUpPayLoad) =
        authenticationRepository.doLogin(payLoad)

    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        val accessTokenKey = stringPreferencesKey("access_token")
        val refreshTokenKey = stringPreferencesKey("refresh_token")
        val isLoggedIn = booleanPreferencesKey("is_logged_in")

        dataStoreManager.put(accessTokenKey, accessToken)
        dataStoreManager.put(refreshTokenKey, refreshToken)
        dataStoreManager.put(isLoggedIn, true)
    }

    suspend fun clearStoreData(){
        dataStoreManager.clearData()
    }
}