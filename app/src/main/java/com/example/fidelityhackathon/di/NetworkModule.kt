package com.example.fidelityhackathon.di

import com.example.fidelityhackathon.BuildConfig
import com.example.fidelityhackathon.data.services.AuthenticationAPIService
import com.example.fidelityhackathon.data.services.CommunityAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providesBaseUrl() = BuildConfig.API_BASE_URL

    @Provides
    @Singleton
    fun providesOkHTTPClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideAuthenticationAPIService(retrofit: Retrofit): AuthenticationAPIService =
        retrofit.create(AuthenticationAPIService::class.java)

    @Provides
    @Singleton
    fun providesCommunityAPIService(retrofit: Retrofit): CommunityAPIService =
        retrofit.create(CommunityAPIService::class.java)
}