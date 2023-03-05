package com.nds.filmstarterapp.repository

import com.nds.filmstarterapp.model.kinopoisk_category.Category
import com.nds.filmstarterapp.model.kinopoisk_film.FilmKinopoisk
import com.nds.filmstarterapp.model.kinopoisk_films.FilmList
import com.nds.filmstarterapp.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FilmApiService {
    @GET("./movie")
    suspend fun getFilms(
        @Query("name") name: String,
        @Query("genres.name") category: Array<String>,
        @Query("token") token: String = API_KEY,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("selectFields") selectFields: Array<String> = array,
    ): Response<FilmList>

    @GET("./movie/possible-values-by-field")
    suspend fun getCategory(
        @Query("field") field: String = "genres.name",
        @Query("token") token: String = API_KEY,
    ): Response<Category>

    @GET("movie/{id}")
    suspend fun getFilm(
        @Path("id") id: Int,
        @Query("token") token: String = API_KEY,
    ): Response<FilmKinopoisk>

    companion object QueryMap {
        val array = arrayOf(
            "id",
            "name",
            "poster.url",
            "ageRating",
            "description",
            "rating.kp",
            "premiere"
        )
    }
}