package com.nds.filmstarterapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nds.filmstarterapp.FilmViewModel
import com.nds.filmstarterapp.ui.theme.FilmStarterAppTheme

@Composable
fun FilmFirstScreen(navController: NavController, viewModel: FilmViewModel) {
    val filmList = viewModel.getFilm()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 50.dp),
        content = {
            items(filmList.size) { index: Int ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    FilmCard(navController = navController, film = filmList[index])
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    FilmStarterAppTheme {
        val viewModel = FilmViewModel()
        FilmFirstScreen(navController = rememberNavController(), viewModel = viewModel)
    }
}