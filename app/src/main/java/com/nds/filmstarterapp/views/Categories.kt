package com.nds.filmstarterapp.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nds.filmstarterapp.utils.Category

@Composable
fun Categories() {
    LazyRow(contentPadding = PaddingValues(start = 20.dp)) {
        items(Category.values()) {
            CategoryChips(it)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryChips(category: Category) {
    Chip(
        onClick = { },
        modifier = Modifier
            .padding(end = 6.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onSecondary),
        colors = ChipDefaults.chipColors(backgroundColor = Color.Transparent)
    ) {
        Text(text = category.title)
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesPreview() {
    Categories()
}

@Preview(showBackground = true)
@Composable
fun CategoryChipsPreview() {
    CategoryChips(Category.ACTION)
}