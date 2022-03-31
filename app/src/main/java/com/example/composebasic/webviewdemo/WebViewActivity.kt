package com.example.composebasic.webviewdemo

import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.composebasic.webviewdemo.ui.theme.ComposeBasicTheme
import com.example.composebasic.webviewdemo.ui.theme.Purple700

class WebViewActivity : ComponentActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                val progression = remember {
                    mutableStateOf(0f)
                }
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        LinearProgressIndicator(
                            progress = progression.value / 100,
                            modifier = Modifier.fillMaxWidth(),
                            color = Purple700
                        )
                        AndroidView(factory = { context ->
                            webView = WebView(context)
                            webView.apply {
                                settings.javaScriptEnabled = true
                                webViewClient = object : WebViewClient() {}
                                loadUrl("https://www.oschina.net/")
                            }

                        },
                            modifier = Modifier.fillMaxSize(), update = { webView ->
                                webView.webChromeClient = object : WebChromeClient() {
                                    override fun onProgressChanged(
                                        view: WebView?,
                                        newProgress: Int
                                    ) {
                                        progression.value = newProgress.toFloat()
                                        super.onProgressChanged(view, newProgress)
                                    }
                                }
                            })
                    }

                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode==KeyEvent.KEYCODE_BACK&&webView.canGoBack()){
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}