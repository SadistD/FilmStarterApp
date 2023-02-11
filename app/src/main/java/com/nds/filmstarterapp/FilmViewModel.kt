package com.nds.filmstarterapp

import androidx.lifecycle.ViewModel
import com.nds.filmstarterapp.model.Film

class FilmViewModel : ViewModel() {
    fun getFilm(): List<Film> {
        val film1 = Film(
            1,
            R.string.the_green_mile,
            R.drawable.the_green_mile,
            1999,
            9.1,
            R.string.the_green_mile_description
        )
        val film2 = Film(
            2,
            R.string.the_shawshank_redemption,
            R.drawable.the_shawshank_redemption,
            1994,
            9.1,
            R.string.the_shawshank_redemption_description
        )
        return listOf(film1, film2)
    }
}