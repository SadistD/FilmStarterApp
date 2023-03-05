package com.nds.filmstarterapp.model.kinopoisk_films


import com.google.gson.annotations.SerializedName

data class FilmInList(
    @SerializedName("ageRating")
    val ageRating: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster")
    val poster: Poster?,
    @SerializedName("premiere")
    val premiere: Premiere?,
    @SerializedName("rating")
    val rating: Rating?
)