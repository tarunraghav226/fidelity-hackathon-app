package com.example.fidelityhackathon.di

import android.content.Context
import com.example.fidelityhackathon.storage.DataStoreInterface
import com.example.fidelityhackathon.storage.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesDataStoreManager(@ApplicationContext appContext: Context): DataStoreInterface {
        return DataStoreManager(appContext)
    }
}