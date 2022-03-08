package com.example.composebasic.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColumnSample() {
    Column(
        modifier = Modifier
            .size(200.dp)
            .background(Color.Green),
        //verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Column first item",
            modifier = Modifier.weight(1f, false)
        )
        Text(text = "Column second item")
    }
}

@Preview
@Composable
fun ColumnSamplePreview() {
    ColumnSample()
}

