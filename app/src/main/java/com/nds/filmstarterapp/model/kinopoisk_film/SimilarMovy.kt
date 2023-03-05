package com.nds.filmstarterapp.model.kinopoisk_film


import com.google.gson.annotations.SerializedName

data class SimilarMovy(
    @SerializedName("alternativeName")
    val alternativeName: String?,
    @SerializedName("enName")
    val enName: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster")
    val poster: Poster?,
    @SerializedName("type")
    val type: String?
)