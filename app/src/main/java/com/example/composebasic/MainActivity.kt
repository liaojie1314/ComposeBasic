package com.example.composebasic

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.composebasic.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main)
        val composeView = findViewById<ComposeView>(R.id.composeView)

        composeView.setContent {
            Column {
                Greeting(name = "Android")
                AndroidView(factory = {
                    TextView(it)
                }) {
                    it.apply {
                        text = "这是 Compose View 里面的 Text"
                    }
                }
            }
        }

//        setContent {
//            ComposeBasicTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
//            }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showSystemUi = true, device = Devices.PIXEL_2)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        Greeting("Android")
    }
}