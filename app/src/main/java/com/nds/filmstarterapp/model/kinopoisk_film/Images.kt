package com.nds.filmstarterapp.model.kinopoisk_film


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("framesCount")
    val framesCount: Int?
)