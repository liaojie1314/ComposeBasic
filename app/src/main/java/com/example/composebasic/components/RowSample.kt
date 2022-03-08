package com.example.composebasic.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RowSample() {
    Row(
        modifier = Modifier
            .size(400.dp)
            .background(Color.Green),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Column first item",
            modifier = Modifier.weight(1f)
                .background(Color.Red)
        )
        Text(
            text = "Column second item",
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun RowSamplePreview() {
    RowSample()
}

