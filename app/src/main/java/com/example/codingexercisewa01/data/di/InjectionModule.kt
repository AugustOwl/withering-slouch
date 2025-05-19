package com.example.codingexercisewa01.data.di

import com.example.codingexercisewa01.data.service.CountryApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InjectionModule {

    @Provides
    @Singleton
    fun provideCountryApiService(): CountryApiService {
        return Retrofit.Builder()
            // possibly move url
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApiService::class.java)
    }
}