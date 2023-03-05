package com.nds.filmstarterapp.model.kinopoisk_category


import com.google.gson.annotations.SerializedName

data class CategoryItem(
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    var isChecked: Boolean = false
)