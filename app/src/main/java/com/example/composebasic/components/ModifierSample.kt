package com.example.composebasic.components


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ModifierSample() {
    Text(
        text = "Hello Modifiers",
        //style = TextStyle(background = Color.Blue),
        modifier = Modifier
            .border(1.dp, Color.Red)
            .background(Color.Yellow)
            .padding(8.dp)
            .clickable {
                Log.i("TAG", "点击事件")
            },//注意顺序
        //modifier = Modifier
        //    .padding(8.dp)
        //    .background(Color.Yellow)
    )
}

@Preview
@Composable
fun ModifierSamplePreview() {
    ModifierSample()
}

