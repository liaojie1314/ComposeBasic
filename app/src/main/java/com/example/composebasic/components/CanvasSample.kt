package com.example.composebasic.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.R

@Composable
fun CanvasSample() {
    var imageBitmap: ImageBitmap? = null
    with(LocalContext.current) {
        imageBitmap = ImageBitmap.imageResource(id = R.drawable.image)
    }
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .background(Color.LightGray)
    ) {
        drawLine(
            Color.Yellow,
            start = Offset(x = 0f, y = 10f),
            end = Offset(x = 100f, y = 200f),
            strokeWidth = 10f,
            cap = StrokeCap.Round
        )

        drawRect(Color.Yellow, size = Size(100f, 100f), topLeft = Offset(x = 10f, y = 0f))

        imageBitmap?.let { drawImage(it) }

        drawRoundRect(
            Color.Green,
            size = Size(100f, 100f),
            cornerRadius = CornerRadius(20f, 20f),
            style = Stroke(width = 10f)
        )

        drawCircle(Color.Red, style = Stroke(width = 10f))

        drawOval(Color.Cyan)

        drawArc(
            Color.Blue,
            startAngle = 0f,
            sweepAngle = 30f,
            useCenter = false,
            style = Stroke(width = 10f)
        )

        drawPoints(
            listOf(Offset(10f, 10f), Offset(20f, 50f), Offset(40f, 60f), Offset(60f, 100f)),
            pointMode = PointMode.Lines,
            Color.Black,
            strokeWidth = 10f,
            cap = StrokeCap.Round
        )

    }
}

@Preview
@Composable
fun CanvasSamplePreview() {
    CanvasSample()
}

