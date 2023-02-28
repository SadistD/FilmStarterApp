package com.nds.filmstarterapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.nds.filmstarterapp.R
import com.nds.filmstarterapp.model.kinopoisk_film.FilmInList
import com.nds.filmstarterapp.ui.theme.*
import com.nds.filmstarterapp.viewModel.FilmViewModel
import com.nds.filmstarterapp.viewModel.PreviewViewModel
import com.nds.filmstarterapp.views.AgeRating
import com.nds.filmstarterapp.views.CategoryChips
import com.nds.filmstarterapp.views.RatingBar
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilmDetailsScreen(navController: NavController, viewModel: FilmViewModel, filmId: Int) {
    val film = viewModel.films.collectAsState().value.first { it.id == filmId }
    val configuration = LocalConfiguration.current
    BottomSheetScaffold(
        sheetContent = { FrontLayer(film = film) },
        sheetPeekHeight = configuration.screenHeightDp.dp - configuration.screenHeightDp.dp / 3,
        sheetShape = MaterialTheme.shapes.detailScreenColumnShape,
        sheetBackgroundColor = MaterialTheme.colors.background
    ) {
        BackLayer(film = film)
    }
}

@Composable
fun FrontLayer(film: FilmInList) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 32.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CategoryChips(category = film.id.toString())
                    Text(
                        text = ZonedDateTime.parse(film.premiere?.russia?.format() ?: "").format(
                            DateTimeFormatter.ofPattern("dd.MM.u")),
                        style = MaterialTheme.typography.date
                    )
                }

                Text(
                    text = film.name ?:"",
                    modifier = Modifier.padding(top = 20.dp, end = 8.dp),
                    style = MaterialTheme.typography.detailScreenName
                )

            }
            AgeRating(ageRating = "${film.ageRating}+", size = 20)
        }
        RatingBar(
            rating = film.rating?.kp ?: 0.0,
            modifier = Modifier
                .padding(start = 20.dp, top = 8.dp)
                .height(16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = film.description ?:"",
            modifier = Modifier.padding(start = 20.dp, end = 8.dp),
            style = MaterialTheme.typography.detailScreenDescription
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.actors),
            style = MaterialTheme.typography.filmDetailScreenHeading,
            modifier = Modifier.padding(start = 20.dp)
        )
//        Actors(actors = film.actors)
    }
}

@Composable
fun BackLayer(film: FilmInList) {
    AsyncImage(model = film.poster?.url ?: "",
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
            filmId = 2
        )
    }
}