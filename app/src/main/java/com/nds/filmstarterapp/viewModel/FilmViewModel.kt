package com.nds.filmstarterapp.viewModel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nds.filmstarterapp.model.kinopoisk_film.*
import com.nds.filmstarterapp.repository.common.Common
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

interface FilmViewModel {
    val films: StateFlow<List<FilmInList>>
    val searchFieldState: State<SearchFieldState>

    fun changeSearchFieldFocus()
    fun changeSearchFieldVisible()
    fun changeSearchFieldText(searchText: String)
}

class MainViewModel(application: Application) : AndroidViewModel(application), FilmViewModel {
    private val context = application
    private val filmsList = MutableStateFlow(mutableListOf<FilmInList>())
    override val films: StateFlow<List<FilmInList>> = filmsList.asStateFlow()

    init {
        getFilms("", emptyList(), "HAZCP41-NGQ4XYG-H1SMS2R-0JH96E7")
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

    }

    private fun getFilms(
        name: String,
        category: List<String>,
        token: String,
        page: Int = 1,
        limit: Int = 30,
    ) {

        viewModelScope.launch {
            val response = Common.retrofitService.getFilms(
                name = name,
                category = category,
                token = token,
                page = page,
                limit = limit
            )


            filmsList.value = response.body()!!.docs!!.map { it }.toMutableList()

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

@Suppress("SpellCheckingInspection")
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
            FilmInList(
                id = 1,
                name = "Зелёная миля",
                poster = Poster("R.drawable.the_green_mile"),
                rating = Rating(9.1),
                description = "В тюрьме для смертников появляется заключенный с божественным даром. Мистическая драма по роману Стивена Кинга",
                ageRating = 16,
                premiere = Premiere("19.08.2016")
            )
        )
    )
    override val films: StateFlow<List<FilmInList>>
        get() = filmsList
}
