package com.nds.filmstarterapp.repository

import com.nds.filmstarterapp.model.kinopoisk_film.FilmList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitApi {
    @GET("./movie")
    suspend fun getFilms(
        @Query("name") name: String,
        @Query("genres") category: List<String>,
        @Query("token") token: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
        @Query("selectFields") selectFields: Array<String> = array,
    ): Response<FilmList>

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