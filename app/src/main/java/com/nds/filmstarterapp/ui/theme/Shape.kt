package com.nds.filmstarterapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val Shapes.filmCardImageShape: Shape
    get() = RoundedCornerShape(size = 10.dp)

val Shapes.detailScreenImageShape: Shape
    get() = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp
    )

val Shapes.detailScreenColumnShape: Shape
    get() = RoundedCornerShape(
        topStart = 30.dp,
        topEnd = 30.dp
    )