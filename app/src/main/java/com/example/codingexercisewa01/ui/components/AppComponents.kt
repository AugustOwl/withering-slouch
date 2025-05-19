package com.example.codingexercisewa01.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.codingexercisewa01.R
import com.example.codingexercisewa01.data.model.Country
import com.example.codingexercisewa01.ui.states.CountriesComposableUIState as UIState
import com.example.codingexercisewa01.ui.theme.CodingExerciseWA01Theme
import com.example.codingexercisewa01.ui.theme.Purple40
import com.example.codingexercisewa01.viewmodel.CountryViewModel

@Composable
fun Loader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(150.dp)
                .padding(10.dp),
            color = Purple40,
            strokeWidth = 10.dp
        )
    }
}

@Composable
fun CountryList(countryResponse: ArrayList<Country>) {
    LazyColumn (
        modifier = Modifier
            .padding(10.dp),
    )
    {
        items (countryResponse) { country ->
            CountryCard(country)
        }
    }
}

@Composable
fun CountryCard(country: Country) {
    Card(
        modifier = Modifier.padding(4.dp)
            .fillMaxSize()
    ) {
        Column (modifier = Modifier.padding(8.dp).fillMaxWidth()){
            country.flag?.let {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    model = country.flag,
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    placeholder = painterResource(R.drawable.placeholderflaggpng),
                    error = painterResource(R.drawable.placeholderflaggpng)
                )
            }
            Text(text = "Country Name: ${country.name}")
            country.capital?.let { Text(text = "Capital: ${country.capital}") }
            country.capital?.let { Text(text = "Region: ${country.region}") }
            country.language?.let { Text(text = "Language: ${country.language.name}") }
        }
    }
}

// handle Error screen
@Composable
fun GenericError(errorMsg: String) { // can pass either message, object or basic string
    Text(text = "Name: ${errorMsg}")
    Column (modifier =
        Modifier.padding(25.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painter = painterResource(id = R.drawable.error_icon_32),
            contentDescription = errorMsg
        )
        Text(text = errorMsg)
        Text(text = "An Error has occurred and the services are unavailable, please reconnect to wifi and try again...")
    }
}

@Composable
fun CountryLazyList(
    modifier: Modifier = Modifier,
    viewModel: CountryViewModel = hiltViewModel())
{
    val uiState by viewModel.uiState.collectAsState()
    Surface {
        when (uiState) {
            is UIState.Loading -> {
                Loader()
            }
            is UIState.Success -> { // handling success
                CountryList((uiState as UIState.Success).data)
            }
            is UIState.Error -> {
                val error = (uiState as UIState.Error).msg
                Log.v("TSLX","VIEW ERROR MSG: $error ")
                GenericError(error)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodingExerciseWA01Theme {
        CountryLazyList()
    }
}