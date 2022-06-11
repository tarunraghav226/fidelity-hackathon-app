package com.example.fidelityhackathon.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fidelityhackathon.data.repo.CommunityRepository
import com.example.fidelityhackathon.storage.DataStoreInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val communityRepository: CommunityRepository
): ViewModel() {
    suspend fun doCheckUserInCommunity(authenticationToken: String) =
        communityRepository.doCheckUserInCommunity(authenticationToken)

    suspend fun doAddUserInCommunity(authenticationToken: String) =
        communityRepository.doAddUserInCommunity(authenticationToken)

    suspend fun doShowCommunityMembers(authenticationToken: String) =
        communityRepository.doShowCommunityMembers(authenticationToken)
}