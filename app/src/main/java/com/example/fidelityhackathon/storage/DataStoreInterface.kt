package com.example.fidelityhackathon.storage

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreInterface {

    suspend fun <T> put(key: Preferences.Key<T>, value:T)

    suspend fun <T> delete(key: Preferences.Key<T>)

    fun <T> get(key: Preferences.Key<T>, defaultValue: T): Flow<T>

    suspend fun clearData()
}