package com.nds.filmstarterapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nds.filmstarterapp.FilmViewModel
import com.nds.filmstarterapp.PreviewViewModel
import com.nds.filmstarterapp.ui.theme.FilmStarterAppTheme
import com.nds.filmstarterapp.ui.theme.filmFirstScreenHeading
import com.nds.filmstarterapp.views.Categories
import com.nds.filmstarterapp.views.FilmCard
import com.nds.filmstarterapp.views.SearchRow

@Composable
fun FilmFirstScreen(navController: NavController, viewModel: FilmViewModel) {
    val filmList = viewModel.films
    Column(Modifier.fillMaxSize()) {
        SearchRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, end = 20.dp, bottom = 20.dp)
        )
        Text(
            text = "Популярно сейчас",
            modifier = Modifier.padding(start = 20.dp),
            style = MaterialTheme.typography.filmFirstScreenHeading
        )
        Categories()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            content = {
                items(filmList.size) { index: Int ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        FilmCard(navController = navController, film = filmList[index])
                    }
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    FilmStarterAppTheme {

        FilmFirstScreen(
            navController = rememberNavController(), viewModel = PreviewViewModel()
        )
    }
}