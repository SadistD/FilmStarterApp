package com.nds.filmstarterapp.viewModel

data class FiltrateState(
    val searchText: String = SearchFieldState().searchText,
    val searchCategory: List<String>
)
