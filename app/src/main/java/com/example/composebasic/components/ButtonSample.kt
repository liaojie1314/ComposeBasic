package com.example.composebasic.components


import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ButtonSample() {
//    Button(onClick = {
//        Log.d("TAG", "按钮点击事件")
//    }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {
//        Text("Button")
//    }
//    TextButton(onClick = {
//
//    }) {
//        Text(text = "Text Button")
//    }
    OutlinedButton(onClick = {

    }) {
        Text(text = "Text Button")
    }
}

@Preview
@Composable
fun ButtonSamplePreview() {
    ButtonSample()
}

