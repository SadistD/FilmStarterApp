package com.nds.filmstarterapp.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.nds.filmstarterapp.model.kinopoisk_film.FilmInList
import com.nds.filmstarterapp.navigation.NavRoute
import com.nds.filmstarterapp.ui.theme.Shapes
import com.nds.filmstarterapp.ui.theme.filmCardDescription
import com.nds.filmstarterapp.ui.theme.filmCardImageShape
import com.nds.filmstarterapp.ui.theme.filmCardName
import com.nds.filmstarterapp.viewModel.PreviewViewModel

@Composable
fun FilmCard(navController: NavController, film: FilmInList) {
    Column(
        modifier = Modifier
            .clickable { navController.navigate(NavRoute.Detail.route + "/${film.id}") },
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(model = film.poster?.url,
            contentDescription = film.name,
            modifier = Modifier
                .clip(Shapes.filmCardImageShape),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = film.name ?: "",
            modifier = Modifier
                .padding(top = 8.dp, start = 3.dp),
            style = MaterialTheme.typography.filmCardName,
        )

        Text(
            text = film.description ?: "",
            modifier = Modifier
                .padding(top = 10.dp, start = 3.dp),
            style = MaterialTheme.typography.filmCardDescription,
            overflow = TextOverflow.Ellipsis,
            maxLines = 5
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(
                modifier = Modifier.height(13.dp),
                rating = film.rating?.kp ?: 0.0
            )
            AgeRating(ageRating = "${film.ageRating}+")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilmCard() {
    val film = PreviewViewModel().films.collectAsState().value[0]
    FilmCard(navController = rememberNavController(), film = film)
}