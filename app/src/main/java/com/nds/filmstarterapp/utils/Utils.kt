package com.nds.filmstarterapp.utils

import android.content.Context
import com.nds.filmstarterapp.R
import com.nds.filmstarterapp.model.Film
import org.json.JSONObject

fun loadJson(context: Context): List<Film> {
    val json = getJsonDataFromAssets(context)

    return mapJsonToFilm(json)
}

private fun getJsonDataFromAssets(context: Context): String {
    var result = ""

    runCatching {
        val input = context.assets.open("films.json")
        val size = input.available()
        val bytes = ByteArray(size)
        input.read(bytes)
        input.close()
        result = String(bytes)
    }.onFailure {
        it.printStackTrace()
    }

    return result
}

private fun mapJsonToFilm(json: String): List<Film> {
    val films = mutableListOf<Film>()
    runCatching {
        val filmsJson = JSONObject(json).getJSONArray("films")

        for (i in 0..filmsJson.length()) {
            val filmJson = filmsJson.getJSONObject(i)
            val film = Film(
                id = filmJson.getInt("id"),
                name = filmJson.getString("name"),
                photo = when (filmJson.getString("photo")) {
                    "the_green_mile.webp" -> R.drawable.the_green_mile
                    "the_shawshank_redemption.png" -> R.drawable.the_shawshank_redemption
                    else -> R.drawable.ic_launcher_background
                },
                date_publication = filmJson.getInt("date_publication"),
                rating = filmJson.getDouble("rating"),
                description = filmJson.getString("description")
            )
            films.add(film)
        }
    }.onFailure {
        it.printStackTrace()
    }

    return films
}