package com.nds.filmstarterapp.model.kinopoisk_films


import com.google.gson.annotations.SerializedName

data class FilmList(
    @SerializedName("docs")
    val docs: List<FilmInList>?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("pages")
    val pages: Int?,
    @SerializedName("total")
    val total: Int?
)