package com.nds.filmstarterapp.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nds.filmstarterapp.FilmViewModel
import com.nds.filmstarterapp.model.Film
import com.nds.filmstarterapp.ui.theme.FilmStarterAppTheme

@Composable
fun FilmFirstScreen(filmList: List<Film>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        content = {
            items(filmList.size) { index: Int ->
                FilmCard(film = filmList[index])
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FilmStarterAppTheme {
        FilmFirstScreen(FilmViewModel().getFilm())
    }
}