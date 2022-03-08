package com.example.composebasic.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasic.R

@Composable
fun ImageSample() {
    Image(
        painter = painterResource(id = R.drawable.image),
        contentDescription = null,
        //Modifier.size(50.dp),
        //contentScale = ContentScale.Crop,
        //colorFilter = ColorFilter.tint(Color.Red, blendMode = BlendMode.Color)
    )
}

@Preview
@Composable
fun ImageSamplePreview() {
    ImageSample()
}

