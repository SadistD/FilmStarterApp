package com.nds.filmstarterapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchRow() {
    SearchRow()
}