package com.nds.filmstarterapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nds.filmstarterapp.FilmViewModel
import com.nds.filmstarterapp.model.Film
import com.nds.filmstarterapp.ui.theme.FilmStarterAppTheme

@Composable
fun FilmDetailsScreen(navController: NavController, viewModel: FilmViewModel, filmId: Int) {
    val film = viewModel.getFilm().first { it.id == filmId }
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
            .clip(shape = RoundedCornerShape(30.dp))
            .background(color = MaterialTheme.colors.background)
            .height(500.dp)
            .fillMaxWidth()

    ) {
        Text(
            text = "${film.date_publication} год.",
            modifier = Modifier.padding(20.dp)
        )
        Text(
            text = stringResource(id = film.name),
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(start = 20.dp, end = 8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = film.description),
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(start = 20.dp, end = 8.dp)
        )
    }
}

@Composable
fun BackLayer(film: Film) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp)),
        painter = painterResource(id = film.photo),
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    )

}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    FilmStarterAppTheme {
        val viewModel = FilmViewModel()
        FilmDetailsScreen(
            navController = rememberNavController(),
            viewModel = viewModel,
            filmId = 1
        )
    }
}