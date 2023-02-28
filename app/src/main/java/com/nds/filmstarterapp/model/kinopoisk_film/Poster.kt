package com.nds.filmstarterapp.model.kinopoisk_film


import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("url")
    val url: String?
)