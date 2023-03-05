package com.nds.filmstarterapp.model.kinopoisk_film


import com.google.gson.annotations.SerializedName

data class FilmKinopoisk(
    @SerializedName("ageRating")
    val ageRating: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("images")
    val images: Images?,
    @SerializedName("imagesInfo")
    val imagesInfo: ImagesInfo?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("persons")
    val persons: List<Person>?,
    @SerializedName("poster")
    val poster: Poster?,
    @SerializedName("premiere")
    val premiere: Premiere?,
    @SerializedName("rating")
    val rating: Rating?,
    @SerializedName("similarMovies")
    val similarMovies: List<SimilarMovy>?,
    @SerializedName("slogan")
    val slogan: String?,
    @SerializedName("year")
    val year: Int?
)