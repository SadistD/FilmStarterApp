package com.nds.filmstarterapp.utils

import android.content.Context
import com.nds.filmstarterapp.R
import com.nds.filmstarterapp.model.Actor
import com.nds.filmstarterapp.model.Film
import org.json.JSONArray
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
                photo = getResId(filmJson.getString("photo"), R.drawable::class.java)
                    ?: R.drawable.ic_launcher_background,
                date_publication = filmJson.getInt("date_publication"),
                rating = filmJson.getDouble("rating"),
                description = filmJson.getString("description"),
                ageRating = filmJson.getString("ageRating"),
                category = filmJson.getString("category"),
                actors = getActors(filmJson.getJSONArray("actors"))
            )
            films.add(film)
        }
    }.onFailure {
        it.printStackTrace()
    }

    return films
}

private fun getActors(array: JSONArray): List<Actor> {
    runCatching {
        return array
            .toList()
            .map {
                Actor(
                    name = it.getString("name"),
                    photo = getResId(it.getString("photo"), R.drawable::class.java)!!
                )
            }
    }.onFailure {
        it.printStackTrace()
    }
    return emptyList()
}

private fun JSONArray.toList(): List<JSONObject> {
    val jSONObjects = mutableListOf<JSONObject>()
    for (i in 0 until length()) {
        jSONObjects.add(getJSONObject(i))
    }
    return jSONObjects
}
inline fun <reified T> getResId(resName: String, clazz: Class<T>): Int? {

    var result = 0

    runCatching {
        val field = clazz.getDeclaredField(resName)
        result = field.getInt(field)
    }.onFailure { return null }
    return result
}