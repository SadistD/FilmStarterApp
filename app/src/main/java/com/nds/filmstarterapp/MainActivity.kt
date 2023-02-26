package com.nds.filmstarterapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.nds.filmstarterapp.navigation.FilmNavHost
import com.nds.filmstarterapp.ui.theme.FilmStarterAppTheme
import com.nds.filmstarterapp.viewModel.FilmViewModelFactory
import com.nds.filmstarterapp.viewModel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val viewModel: MainViewModel =
                viewModel(factory = FilmViewModelFactory(context.applicationContext as Application))
            val navController = rememberNavController()
            FilmStarterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FilmNavHost(viewModel, navController)
                }
            }
        }
    }
}

