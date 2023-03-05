package com.nds.filmstarterapp.model.kinopoisk_film


import com.google.gson.annotations.SerializedName

data class ImagesInfo(
    @SerializedName("framesCount")
    val framesCount: Int?
)