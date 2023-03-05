package com.nds.filmstarterapp.model.kinopoisk_film


import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("enName")
    val enName: String?,
    @SerializedName("enProfession")
    val enProfession: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("profession")
    val profession: String?
)