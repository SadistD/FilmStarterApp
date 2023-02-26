package com.nds.filmstarterapp.model

import androidx.annotation.DrawableRes

data class Film(
    val id: Int,
    val name: String,
    @DrawableRes val photo: Int,
    val date_publication: Int,
    val rating: Double,
    val description: String,
    val ageRating: String,
    val category: String,
    val actors: List<Actor> = emptyList()
)
