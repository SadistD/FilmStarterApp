package com.nds.filmstarterapp.model

import androidx.annotation.DrawableRes

data class Actor(
    val name: String,
    @DrawableRes val photo: Int
)
