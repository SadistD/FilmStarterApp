package com.nds.filmstarterapp.viewModel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nds.filmstarterapp.R
import com.nds.filmstarterapp.model.Actor
import com.nds.filmstarterapp.model.Film
import com.nds.filmstarterapp.utils.loadJson
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface FilmViewModel {
    val films: StateFlow<List<Film>>
    val searchFieldState: State<SearchFieldState>

    fun changeSearchFieldFocus()
    fun changeSearchFieldVisible()
    fun changeSearchFieldText(searchText: String)
}

class MainViewModel(application: Application) : AndroidViewModel(application), FilmViewModel {
    private val context = application
    private val filmsList = MutableStateFlow(mutableListOf<Film>())
    override val films = filmsList.asStateFlow()

    init {
        viewModelScope.launch {
            filmsList.value.addAll(loadJson(context))
        }
    }

    private val _searchFieldState: MutableState<SearchFieldState> = mutableStateOf(
        SearchFieldState()
    )

    override val searchFieldState: State<SearchFieldState> = _searchFieldState
    override fun changeSearchFieldFocus() {
        _searchFieldState.value =
            searchFieldState.value.copy(isFocused = !searchFieldState.value.isFocused)
    }

    override fun changeSearchFieldVisible() {
        _searchFieldState.value =
            searchFieldState.value.copy(isVisible = !searchFieldState.value.isVisible)
    }

    override fun changeSearchFieldText(searchText: String) {
        _searchFieldState.value =
            searchFieldState.value.copy(searchText = searchText)
        searchingFilm()
    }

    private fun searchingFilm() {
        viewModelScope.launch {
            filmsList.value =
                loadJson(context).filter {
                    it.name.lowercase().contains(searchFieldState.value.searchText.lowercase())
                }.toMutableList()
        }
    }

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
    private val _searchFieldState: MutableState<SearchFieldState> = mutableStateOf(
        SearchFieldState()
    )
    override val searchFieldState: State<SearchFieldState> = _searchFieldState

    override fun changeSearchFieldVisible() {
        _searchFieldState.value =
            searchFieldState.value.copy(isVisible = !searchFieldState.value.isVisible)
    }

    override fun changeSearchFieldFocus() {
        _searchFieldState.value =
            searchFieldState.value.copy(isFocused = !searchFieldState.value.isFocused)
    }

    override fun changeSearchFieldText(searchText: String) {
        _searchFieldState.value =
            searchFieldState.value.copy(searchText = searchText)
    }

    private val filmsList = MutableStateFlow(
        listOf(
            Film(
                id = 1,
                name = "Зелёная миля",
                photo = R.drawable.the_green_mile,
                date_publication = 1999,
                rating = 9.1,
                description = "В тюрьме для смертников появляется заключенный с божественным даром. Мистическая драма по роману Стивена Кинга",
                ageRating = "16+",
                category = "Драма",
                actors = listOf(
                    Actor(
                        name = "Том Хэнкс",
                        photo = R.drawable.tom_hanks
                    ),
                    Actor(
                        name = "Майкл Кларк Дункан",
                        photo = R.drawable.michael_clarke
                    ),
                    Actor(
                        name = "Дэвид Морс",
                        photo = R.drawable.david_morse
                    )
                )
            ),
            Film(
                id = 2,
                name = "Побег из Шоушенка",
                photo = R.drawable.the_shawshank_redemption,
                date_publication = 1994,
                rating = 9.1,
                description = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения.",
                ageRating = "16+",
                category = "Драма",
                actors = listOf(
                    Actor(
                        name = "Том Хэнкс",
                        photo = R.drawable.tom_hanks
                    ),
                    Actor(
                        name = "Майкл Кларк Дункан",
                        photo = R.drawable.michael_clarke
                    ),
                    Actor(
                        name = "Дэвид Морс",
                        photo = R.drawable.david_morse
                    )
                )
            ),
            Film(
                id = 3,
                name = "Зелёная миля",
                photo = R.drawable.the_green_mile,
                date_publication = 1999,
                rating = 9.1,
                description = "В тюрьме для смертников появляется заключенный с божественным даром. Мистическая драма по роману Стивена Кинга",
                ageRating = "16+",
                category = "Драма",
                actors = listOf(
                    Actor(
                        name = "Том Хэнкс",
                        photo = R.drawable.tom_hanks
                    ),
                    Actor(
                        name = "Майкл Кларк Дункан",
                        photo = R.drawable.michael_clarke
                    ),
                    Actor(
                        name = "Дэвид Морс",
                        photo = R.drawable.david_morse
                    )
                )
            ),
            Film(
                id = 4,
                name = "Побег из Шоушенка",
                photo = R.drawable.the_shawshank_redemption,
                date_publication = 1994,
                rating = 9.1,
                description = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения.",
                ageRating = "16+",
                category = "Драма",
                actors = listOf(
                    Actor(
                        name = "Том Хэнкс",
                        photo = R.drawable.tom_hanks
                    ),
                    Actor(
                        name = "Майкл Кларк Дункан",
                        photo = R.drawable.michael_clarke
                    ),
                    Actor(
                        name = "Дэвид Морс",
                        photo = R.drawable.david_morse
                    )
                )
            ),
            Film(
                id = 5,
                name = "Побег из Шоушенка",
                photo = R.drawable.the_shawshank_redemption,
                date_publication = 1994,
                rating = 9.1,
                description = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения.",
                ageRating = "16+",
                category = "Драма",
                actors = listOf(
                    Actor(
                        name = "Том Хэнкс",
                        photo = R.drawable.tom_hanks
                    ),
                    Actor(
                        name = "Майкл Кларк Дункан",
                        photo = R.drawable.michael_clarke
                    ),
                    Actor(
                        name = "Дэвид Морс",
                        photo = R.drawable.david_morse
                    )
                )
            ),
            Film(
                id = 6,
                name = "Побег из Шоушенка",
                photo = R.drawable.the_shawshank_redemption,
                date_publication = 1994,
                rating = 9.1,
                description = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения.",
                ageRating = "16+",
                category = "Драма",
                actors = listOf(
                    Actor(
                        name = "Том Хэнкс",
                        photo = R.drawable.tom_hanks
                    ),
                    Actor(
                        name = "Майкл Кларк Дункан",
                        photo = R.drawable.michael_clarke
                    ),
                    Actor(
                        name = "Дэвид Морс",
                        photo = R.drawable.david_morse
                    )
                )
            ),
            Film(
                id = 7,
                name = "Побег из Шоушенка",
                photo = R.drawable.the_shawshank_redemption,
                date_publication = 1994,
                rating = 9.1,
                description = "Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения.",
                ageRating = "16+",
                category = "Драма",
                actors = listOf(
                    Actor(
                        name = "Том Хэнкс",
                        photo = R.drawable.tom_hanks
                    ),
                    Actor(
                        name = "Майкл Кларк Дункан",
                        photo = R.drawable.michael_clarke
                    ),
                    Actor(
                        name = "Дэвид Морс",
                        photo = R.drawable.david_morse
                    )
                )
            )
        )
    )
    override val films: StateFlow<List<Film>> = filmsList
}