package com.example.fidelityhackathon.data.repo

import com.example.fidelityhackathon.data.services.CommunityAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommunityRepository @Inject constructor(private val communityAPIService: CommunityAPIService) {
    suspend fun doCheckUserInCommunity(authenticationToken: String) =
        withContext(Dispatchers.IO) {communityAPIService.checkUserInCommunity(authenticationToken)}

    suspend fun doAddUserInCommunity(authenticationToken: String) =
        withContext(Dispatchers.IO) {communityAPIService.addUserInCommunity(authenticationToken)}

    suspend fun doShowCommunityMembers(authenticationToken: String) =
        withContext(Dispatchers.IO) {communityAPIService.showCommunityMembers(authenticationToken)}
}