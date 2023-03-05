package com.nds.filmstarterapp.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nds.filmstarterapp.model.kinopoisk_category.CategoryItem
import com.nds.filmstarterapp.ui.theme.category
import com.nds.filmstarterapp.viewModel.FilmViewModel

@Composable
fun Categories(viewModel: FilmViewModel) {
    val categoryList = viewModel.categoryList.collectAsState().value
    LazyRow(contentPadding = PaddingValues(start = 20.dp)) {
        items(categoryList) { category ->
            CategoryChips(category, Modifier.padding(end = 6.dp)) { viewModel.changeCategory(category) }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryChips(
    category: CategoryItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {  }
) {
    Chip(
        onClick = onClick,
        modifier = modifier.height(17.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSecondary),
        colors = ChipDefaults.chipColors(backgroundColor = if (category.isChecked) MaterialTheme.colors.secondary else Color.Transparent)
    ) {
        Text(
            text = category.name.toString(),
            style = MaterialTheme.typography.category
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesPreview() {
//    val categoryList = Category
//    Categories(categoryList)
}

@Preview(showBackground = true)
@Composable
fun CategoryChipsPreview() {
//    CategoryChips(Category.ACTION.title) {

    }
