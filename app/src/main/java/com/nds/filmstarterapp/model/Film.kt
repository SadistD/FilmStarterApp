package com.nds.filmstarterapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Film(
    val id: Int,
    @StringRes val name: Int,
    @DrawableRes val photo: Int,
    val date_publication: Int,
    val rating: Double,
    @StringRes val description: Int
)
