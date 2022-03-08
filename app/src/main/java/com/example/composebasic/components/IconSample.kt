package com.example.composebasic.components


import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasic.R

@Composable
fun IconSample() {
//    Icon(imageVector = Icons.Default.Translate, contentDescription = null, tint = Color.Red)
    IconButton(onClick = {

    }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_android_black_24dp),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun IconSamplePreview() {
    IconSample()
}

