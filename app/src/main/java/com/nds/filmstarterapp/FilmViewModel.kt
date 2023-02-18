package com.nds.filmstarterapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nds.filmstarterapp.model.Film
import com.nds.filmstarterapp.utils.loadJson

interface FilmViewModel {
    val films: List<Film>
}

class MainViewModel(application: Application) : AndroidViewModel(application), FilmViewModel {
    private val context = application
    override val films = loadJson(context)
}

@Suppress("UNCHECKED_CAST")
class FilmViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

class PreviewViewModel : ViewModel(), FilmViewModel {

    override val films = listOf(
        Film(
            id = 1,
            name = "Зелёная миля",
            photo = R.drawable.the_green_mile,
            date_publication = 1999,
            rating = 9.1,
            description = "В тюрьме для смертников появляется заключенный с божественным даром. Мистическая драма по роману Стивена Кинга"
        ),
        Film(
            id = 2,
            name = "Побег из Шоушенка",
            photo = R.drawable.the_shawshank_redemption,
            date_publication = 1994,
            rating = 9.1,
            description = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения."
        ),
        Film(
            id = 3,
            name = "Зелёная миля",
            photo = R.drawable.the_green_mile,
            date_publication = 1999,
            rating = 9.1,
            description = "В тюрьме для смертников появляется заключенный с божественным даром. Мистическая драма по роману Стивена Кинга"
        ),
        Film(
            id = 4,
            name = "Побег из Шоушенка",
            photo = R.drawable.the_shawshank_redemption,
            date_publication = 1994,
            rating = 9.1,
            description = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения."
        ),
        Film(
            id = 5,
            name = "Побег из Шоушенка",
            photo = R.drawable.the_shawshank_redemption,
            date_publication = 1994,
            rating = 9.1,
            description = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения."
        ),
        Film(
            id = 6,
            name = "Побег из Шоушенка",
            photo = R.drawable.the_shawshank_redemption,
            date_publication = 1994,
            rating = 9.1,
            description = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения."
        ),
        Film(
            id = 7,
            name = "Побег из Шоушенка",
            photo = R.drawable.the_shawshank_redemption,
            date_publication = 1994,
            rating = 9.1,
            description = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения."
        )
    )
}