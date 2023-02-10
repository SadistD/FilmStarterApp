package com.nds.filmstarterapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nds.filmstarterapp.FilmViewModel
import com.nds.filmstarterapp.model.Film

@Composable
fun FilmCard(film: Film) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = film.photo),
            contentDescription = stringResource(id = film.name),
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
                .padding(top = 4.dp)
                .width(127.dp),
        )

        Text(
            text = stringResource(id = film.description),
            maxLines = 5,
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight(400),
            modifier = Modifier
                .padding(top = 8.dp)
                .width(147.dp)
        )


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilmCard() {
    val film = FilmViewModel().getFilm()[1]
    FilmCard(film = film)
}