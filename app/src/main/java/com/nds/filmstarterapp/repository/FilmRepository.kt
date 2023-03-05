package com.nds.filmstarterapp.repository

import com.nds.filmstarterapp.model.kinopoisk_category.CategoryItem
import com.nds.filmstarterapp.model.kinopoisk_film.FilmKinopoisk
import com.nds.filmstarterapp.model.kinopoisk_films.FilmInList
import com.nds.filmstarterapp.utils.API_KEY
import com.nds.filmstarterapp.viewModel.SearchFieldState

class FilmRepository {

    suspend fun getFilmList(
        search: SearchFieldState = SearchFieldState(),
        page: Int = 1,
        limit: Int = 10,
        token: String = API_KEY,
    ): List<FilmInList> {
        val categoryArray: Array<String> = search.categoryList.toTypedArray()
        val name = search.searchText
        return try {
            val response = RetrofitClient.getClient().getFilms(
                name = name,
                category = categoryArray,
                token = token,
                page = page,
                limit = limit
            )
            response.body()!!.docs!!.map { it }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getCategory(): List<CategoryItem> {
        return try {
            RetrofitClient.getClient().getCategory()
                .body()!!
                .map { it }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getFilm(
        id: Int,
    ): FilmKinopoisk {
        return RetrofitClient.getClient().getFilm(id).body()!!
    }
}
