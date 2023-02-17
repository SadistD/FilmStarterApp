package com.nds.filmstarterapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nds.filmstarterapp.FilmViewModel
import com.nds.filmstarterapp.screen.FilmDetailsScreen
import com.nds.filmstarterapp.screen.FilmFirstScreen
import com.nds.filmstarterapp.utils.Constants

sealed class NavRoute(val route: String) {

    object Start : NavRoute(Constants.Screen.FIRST_SCREEN)

    object Detail : NavRoute(Constants.Screen.DETAILS_SCREEN)
}

@Composable
fun FilmNavHost(viewModel: FilmViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) {
            FilmFirstScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(NavRoute.Detail.route + "/{${Constants.Key.ID}}") { backStackEntry ->
            backStackEntry.arguments?.getString(Constants.Key.ID)?.let {
                FilmDetailsScreen(
                    navController = navController,
                    viewModel = viewModel,
                    filmId = it.toInt()
                )
            }
        }

    }
}