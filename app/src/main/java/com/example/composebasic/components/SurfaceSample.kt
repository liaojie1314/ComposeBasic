package com.example.composebasic.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.R

@Composable
fun SurfaceSample() {
    Column {
        Surface(
            modifier = Modifier
                .height(100.dp)
                .padding(10.dp),
            color = Color.Yellow,
            //CutCornerShape 剪切角
            //RoundedCornerShape 圆角
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Green),
            elevation = 10.dp//阴影效果
        ) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
fun SurfaceSamplePreview() {
    SurfaceSample()
}

