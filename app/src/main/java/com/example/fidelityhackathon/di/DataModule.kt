package com.example.fidelityhackathon.di

import android.content.Context
import com.example.fidelityhackathon.storage.DataStoreInterface
import com.example.fidelityhackathon.storage.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesDataStoreManager(@ApplicationContext appContext: Context): DataStoreInterface {
        return DataStoreManager(appContext)
    }
}