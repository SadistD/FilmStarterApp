package com.nds.filmstarterapp.repository.common

import com.nds.filmstarterapp.repository.RetrofitApi
import com.nds.filmstarterapp.repository.RetrofitClient


object Common {
    private const val BASE_URL = "https://api.kinopoisk.dev/v1/"
    val retrofitService: RetrofitApi
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitApi::class.java)
}
