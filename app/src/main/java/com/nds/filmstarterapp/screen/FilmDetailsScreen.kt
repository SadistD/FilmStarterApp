package com.nds.filmstarterapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nds.filmstarterapp.model.Film
import com.nds.filmstarterapp.ui.theme.*
import com.nds.filmstarterapp.viewModel.FilmViewModel
import com.nds.filmstarterapp.viewModel.PreviewViewModel

@Composable
fun FilmDetailsScreen(navController: NavController, viewModel: FilmViewModel, filmId: Int) {
    val film = viewModel.films.collectAsState().value.first { it.id == filmId }
    Box(modifier = Modifier.fillMaxSize()) {
        BackLayer(film = film)

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)

        ) {
            FrontLayer(film = film)
        }

    }
}

@Composable
fun FrontLayer(film: Film) {
    Column(
        modifier = Modifier
            .clip(Shapes.detailScreenColumnShape)
            .background(color = MaterialTheme.colors.background)
            .height(500.dp)
            .fillMaxWidth()

    ) {
        Text(
            text = "${film.date_publication} год.",
            modifier = Modifier.padding(20.dp)
        )
        Text(
            text = film.name,
            modifier = Modifier.padding(start = 20.dp, end = 8.dp),
            style = MaterialTheme.typography.detailScreenName
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = film.description,
            modifier = Modifier.padding(start = 20.dp, end = 8.dp),
            style = MaterialTheme.typography.detailScreenDescription
        )
    }
}

@Composable
fun BackLayer(film: Film) {
    Image(
        painter = painterResource(id = film.photo),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .clip(Shapes.detailScreenImageShape),

        contentScale = ContentScale.FillWidth
    )
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    FilmStarterAppTheme {
        FilmDetailsScreen(
            navController = rememberNavController(),
            viewModel = PreviewViewModel(),
            filmId = 1
        )
    }
}