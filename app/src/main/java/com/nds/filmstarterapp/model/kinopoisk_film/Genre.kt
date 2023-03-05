package com.nds.filmstarterapp.model.kinopoisk_film


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name")
    val name: String?
)