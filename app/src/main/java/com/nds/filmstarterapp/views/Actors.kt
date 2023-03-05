package com.nds.filmstarterapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nds.filmstarterapp.model.kinopoisk_film.Person
import com.nds.filmstarterapp.ui.theme.Shapes
import com.nds.filmstarterapp.ui.theme.filmCardImageShape
import com.nds.filmstarterapp.ui.theme.filmCardName

@Composable
fun Actors(actors: List<Person>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(20.dp)
    ) {
        items(actors) { actor ->
            Actor(person = actor)
        }
    }
}

@Composable
fun Actor(person: Person) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
            model = person.photo,
            contentDescription = person.name,
            modifier = Modifier
                .width(150.dp)
                .height(196.dp)
                .clip(Shapes.filmCardImageShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = person.name.toString(),
            modifier = Modifier
                .padding(top = 12.dp),
            style = MaterialTheme.typography.filmCardName,
        )
    }
}

@Preview
@Composable
fun ActorsPreview() {
//    val actors = PreviewViewModel().films.collectAsState().value[1].actors
//    Actors(actors)
}

@Preview
@Composable
fun ActorPreview() {
//    val actor = PreviewViewModel().films.collectAsState().value[1].actors.first()
//    Actor(actor)
}