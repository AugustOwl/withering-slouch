package com.example.codingexercisewa01.data.repository

import com.example.codingexercisewa01.data.model.Country
import com.example.codingexercisewa01.data.model.CountryResponse
import com.example.codingexercisewa01.data.service.CountryApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val countryApiService: CountryApiService
){
    fun getAllCountries(): Flow<ArrayList<Country>> = flow {
        emit(countryApiService.getAllCountries())
    }
}
