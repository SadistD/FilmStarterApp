package com.nds.filmstarterapp.views

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = MaterialTheme.colors.onSecondary,
) {
    val usedRating = rating * (stars.toDouble() / 10)
    val filledStars = floor(usedRating).toInt()
    val unfilledStars = (stars - ceil(usedRating)).toInt()
    val halfStar = !(usedRating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = starsColor
            )
        }

        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null,
                tint = starsColor
            )
        }

        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}

class RatingParams: PreviewParameterProvider<Double>{
    override val values: Sequence<Double>
        get() = (0..9).map { it.toDouble() }.asSequence()
}

@Preview(showBackground = true)
@Composable
fun RatingBarPreview(
    @PreviewParameter (RatingParams::class)
    rating: Double
) {
    RatingBar(rating = rating)
}