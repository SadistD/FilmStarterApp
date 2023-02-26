package com.nds.filmstarterapp.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.nds.filmstarterapp.viewModel.PreviewViewModel
import com.nds.filmstarterapp.viewModel.SearchFieldState

@Composable
fun SearchRow(
    searchFieldState: SearchFieldState,
    changeSearchFieldVisible: () -> Unit,
    changeSearchFieldText: (searchText: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .focusRequester(focusRequester),

                value = searchFieldState.searchText,
                cursorBrush = SolidColor(MaterialTheme.colors.primary),
                textStyle = LocalTextStyle.current.copy(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 14.sp),
                onValueChange = changeSearchFieldText,
                enabled = searchFieldState.isVisible,
                singleLine = true,
            )
        }
        Box{

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .clickable(onClick = changeSearchFieldVisible)
            )
        }
        LaunchedEffect(searchFieldState.isFocused) {
            if (searchFieldState.isFocused) {
                focusRequester.requestFocus()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchRow() {

    SearchRow(
        searchFieldState = SearchFieldState(
            searchText = "test",
            isVisible = true
        ),
        changeSearchFieldVisible = { PreviewViewModel().changeSearchFieldVisible() },
        changeSearchFieldText = { PreviewViewModel().changeSearchFieldText(it) }
    )
}