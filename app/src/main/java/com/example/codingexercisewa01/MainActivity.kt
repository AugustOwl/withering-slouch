package com.example.codingexercisewa01

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.codingexercisewa01.ui.components.CountryLazyList
import com.example.codingexercisewa01.ui.theme.CodingExerciseWA01Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (true) { // branching path for the versions of the screen... (or can do brand new activity)
            enableEdgeToEdge()
            setContent {
                CodingExerciseWA01Theme {
                    Scaffold() { innerPadding ->
                        CountryLazyList(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
