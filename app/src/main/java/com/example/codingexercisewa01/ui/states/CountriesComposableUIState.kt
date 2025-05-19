package com.example.codingexercisewa01.ui.states

import com.example.codingexercisewa01.data.model.Country

sealed class CountriesComposableUIState {
    // UI state responsible for dealing with state of countries.json load
    object Loading: CountriesComposableUIState()
    data class Success(val data: ArrayList<Country>): CountriesComposableUIState()
    data class Error(val msg: String): CountriesComposableUIState()
}
