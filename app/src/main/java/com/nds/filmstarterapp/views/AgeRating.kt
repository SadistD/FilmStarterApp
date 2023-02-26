package com.nds.filmstarterapp.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nds.filmstarterapp.ui.theme.ageRating

@Composable
fun AgeRating(ageRating: String, size: Int = 10) {
    Box(
        modifier = Modifier
            .size((size * 2.1).dp)
            .border(
                width = (size / 20.0).dp,
                color = MaterialTheme.colors.onSecondary,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = ageRating,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.ageRating,
            fontSize = size.sp
        )

    }
}

@Preview
@Composable
fun PreviewAgeRating() {
    AgeRating(ageRating = "6+")
}