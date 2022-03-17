package com.example.composebasic.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyVerticalGridSample() {
    val datas = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(datas) {
            Text("Grid Item $it")
        }
    }
}

@Preview
@Composable
fun LazyVerticalGridSamplePreview() {
    LazyVerticalGridSample()
}

