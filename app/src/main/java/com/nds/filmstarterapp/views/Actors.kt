package com.nds.filmstarterapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.nds.filmstarterapp.model.Actor
import com.nds.filmstarterapp.ui.theme.Shapes
import com.nds.filmstarterapp.ui.theme.filmCardImageShape
import com.nds.filmstarterapp.ui.theme.filmCardName
import com.nds.filmstarterapp.viewModel.PreviewViewModel

@Composable
fun Actors(actors: List<Actor>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(20.dp)
    ) {
        items(actors) { actor ->
            Actor(actor = actor)
        }
    }
}

@Composable
fun Actor(actor: Actor) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(id = actor.photo),
            contentDescription = actor.name,
            modifier = Modifier
                .width(150.dp)
                .height(196.dp)
                .clip(Shapes.filmCardImageShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = actor.name,
            modifier = Modifier
                .padding(top = 12.dp),
            style = MaterialTheme.typography.filmCardName,
        )
    }
}

@Preview
@Composable
fun ActorsPreview() {
    val actors = PreviewViewModel().films.collectAsState().value[1].actors
    Actors(actors)
}

@Preview
@Composable
fun ActorPreview() {
    val actor = PreviewViewModel().films.collectAsState().value[1].actors.first()
    Actor(actor)
}