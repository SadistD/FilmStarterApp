package com.nds.filmstarterapp.viewModel

data class SearchFieldState(
    val searchText: String = "",
    val isVisible: Boolean = false,
    val isFocused: Boolean = false,
    val categoryList: List<String> = listOf()
)
