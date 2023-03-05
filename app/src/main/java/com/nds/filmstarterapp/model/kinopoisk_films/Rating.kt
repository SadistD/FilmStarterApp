package com.nds.filmstarterapp.model.kinopoisk_films


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("kp")
    val kp: Double?
)