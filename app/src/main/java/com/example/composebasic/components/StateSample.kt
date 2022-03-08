package com.example.composebasic.components


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StateSample() {
    var count by remember {
        mutableStateOf(1)
    }
    Text(text = "Text${count}", modifier = Modifier
        .padding(8.dp)
        .clickable {
            count++
            Log.d("TAG", "第${count}次")
        })

}

@Preview
@Composable
fun StateSamplePreview() {
    StateSample()
}

