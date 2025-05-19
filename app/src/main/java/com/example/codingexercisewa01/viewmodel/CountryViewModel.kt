package com.example.codingexercisewa01.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codingexercisewa01.data.repository.CountryRepository
import com.example.codingexercisewa01.ui.states.CountriesComposableUIState as UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountryViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiState

    init {
        Log.v("TSLX","ViewModel Init")
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch {
            delay(1500L) // Fake loading time to demonstrate loader
            countryRepository.getAllCountries()
                .onStart {
                    _uiState.value = UIState.Loading
                    Log.v("TSLX","FETCH Init")
                }
                .catch {e -> _uiState.value = UIState.Error(e.message ?: "unknown error")}
                .collect { fetchedCountries -> _uiState.value = UIState.Success (fetchedCountries) }
        }
    }

}