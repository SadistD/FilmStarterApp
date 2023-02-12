package com.nds.filmstarterapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nds.filmstarterapp.FilmViewModel
import com.nds.filmstarterapp.model.Film
import com.nds.filmstarterapp.navigation.NavRoute

@Composable
fun FilmCard(navController: NavController, film: Film) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(365.dp)
            .clickable { navController.navigate(NavRoute.Detail.route + "/${film.id}") },
        horizontalAlignment = Alignment.Start
    ) {

        Image(
            painter = painterResource(id = film.photo),
            contentDescription = stringResource(id = film.name),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(150.dp)
                .height(216.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Text(
            text = stringResource(id = film.name),
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            modifier = Modifier
                .padding(top = 8.dp, start = 3.dp)
                .width(127.dp),
        )

        Text(
            text = stringResource(id = film.description),
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight(400),
            modifier = Modifier
                .padding(top = 10.dp, start = 3.dp)
                .width(147.dp)
                .height(84.dp)
        )


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilmCard() {
    val film = FilmViewModel().getFilm()[1]
    FilmCard(navController = rememberNavController(), film = film)
}