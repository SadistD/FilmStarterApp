package com.nds.filmstarterapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nds.filmstarterapp.model.kinopoisk_category.CategoryItem
import com.nds.filmstarterapp.model.kinopoisk_film.FilmKinopoisk
import com.nds.filmstarterapp.model.kinopoisk_films.*
import com.nds.filmstarterapp.repository.FilmRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

interface FilmViewModel {
    val films: StateFlow<List<FilmInList>>
    val searchFieldState: StateFlow<SearchFieldState>
    val categoryList: StateFlow<List<CategoryItem>>
    val filmKinopoisk: StateFlow<FilmKinopoisk?>

    fun changeSearchFieldFocus()
    fun changeSearchFieldVisible()
    fun changeSearchFieldText(searchText: String)
    fun changeCategory(category: CategoryItem)
    fun getFilm(id: Int)
}

class MainViewModel : ViewModel(), FilmViewModel {
    private val _filmKinopoisk: MutableStateFlow<FilmKinopoisk?> = MutableStateFlow(null)
    override val filmKinopoisk: StateFlow<FilmKinopoisk?> = _filmKinopoisk.asStateFlow()
    private val repository: FilmRepository = FilmRepository()
    private val filmsList = MutableStateFlow(listOf<FilmInList>())
    override val films: StateFlow<List<FilmInList>> = filmsList.asStateFlow()
    private val _categoryList = MutableStateFlow(listOf<CategoryItem>())
    override val categoryList: StateFlow<List<CategoryItem>> = _categoryList.asStateFlow()

    private val _searchFieldState: MutableStateFlow<SearchFieldState> = MutableStateFlow(
        SearchFieldState()
    )

    override val searchFieldState: StateFlow<SearchFieldState> = _searchFieldState.asStateFlow()

    init {
        getFilms()
        getCategory()
    }

    override fun changeSearchFieldFocus() {
        viewModelScope.launch {
            _searchFieldState.emit(searchFieldState.value.copy(isFocused = !searchFieldState.value.isFocused))
        }

    }

    override fun changeSearchFieldVisible() {
        viewModelScope.launch {
            _searchFieldState.emit(searchFieldState.value.copy(isVisible = !searchFieldState.value.isVisible))
        }

    }

    override fun changeSearchFieldText(searchText: String) {
        viewModelScope.launch {
            _searchFieldState.emit(searchFieldState.value.copy(searchText = searchText))
            getFilms()
        }

    }

    override fun changeCategory(category: CategoryItem) {
        viewModelScope.launch {
            if (category.isChecked) {
                category.isChecked = false
                _searchFieldState.emit(searchFieldState.value.copy(
                    categoryList = searchFieldState.value.categoryList.filter { it != category.name }
                ))
            } else {
                category.isChecked = true
                _searchFieldState.emit(
                    searchFieldState.value.copy(
                        categoryList = searchFieldState.value.categoryList.plus(category.name.toString())
                    )
                )
            }
            getFilms()
        }
    }

    override fun getFilm(id: Int) {
        viewModelScope.launch {

            _filmKinopoisk.value = repository.getFilm(id)
        }


    }

    private fun getCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            _categoryList.emit(repository.getCategory())
        }
    }

    private fun getFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("search", searchFieldState.value.toString())
            filmsList.emit(
                repository.getFilmList(
                    search = searchFieldState.value,
                )
            )
        }
    }
}

@Suppress("SpellCheckingInspection")
class PreviewViewModel : ViewModel(), FilmViewModel {
    private val _searchFieldState: MutableStateFlow<SearchFieldState> = MutableStateFlow(
        SearchFieldState()
    )
    override val searchFieldState: StateFlow<SearchFieldState> = _searchFieldState
    override val categoryList: StateFlow<List<CategoryItem>> =
        MutableStateFlow(mutableListOf(CategoryItem("", ""))).asStateFlow()
    override val filmKinopoisk: StateFlow<FilmKinopoisk?>
        get() = TODO("Not yet implemented")

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

    override fun changeCategory(category: CategoryItem) {
        TODO("Not yet implemented")
    }

    override fun getFilm(id: Int) {
        TODO("Not yet implemented")
    }

    private val filmsList = MutableStateFlow(
        listOf(
            FilmInList(
                id = 1,
                name = "Зелёная миля",
                poster = Poster("https://st.kp.yandex.net/images/film_big/435.jpg"),
                rating = Rating(9.1),
                description = "В тюрьме для смертников появляется заключенный с божественным даром. Мистическая драма по роману Стивена Кинга",
                ageRating = 16,
                premiere = Premiere("19.08.2016")
            )
        )
    )
    override val films: StateFlow<List<FilmInList>>
        get() = filmsList.asStateFlow()
}
