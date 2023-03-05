package com.nds.filmstarterapp.model.kinopoisk_film


import com.google.gson.annotations.SerializedName

data class Premiere(
    @SerializedName("country")
    val country: String?,
    @SerializedName("dvd")
    val dvd: String?,
    @SerializedName("world")
    val world: String?
)