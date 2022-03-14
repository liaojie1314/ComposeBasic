package com.example.composebasic.components


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun LifecycleSample() {
    var count by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(count/*Unit*/) {
        Log.i("TAG", "LaunchedEffect")
    }
    Log.i("TAG", "LifecycleSample")
    val lifeCycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifeCycleOwner) {
        val lifecycleEventObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    //进入后台调用播放器暂停方法
                }
                Lifecycle.Event.ON_RESUME -> {
                    //回到前台继续播放
                }
                else -> {

                }
            }
        }
        lifeCycleOwner.lifecycle.addObserver(lifecycleEventObserver)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(lifecycleEventObserver)
        }
    }

    Column {
        Text("" + count)
        Button(onClick = {
            count++
        }) {
            Text("Button")
        }
        if (count == 3) {
            SubScreen(count = count)
        }
    }
}

@Composable
fun SubScreen(count: Int) {
    DisposableEffect(Unit) {
        Log.i("TAG", "DisposableEffect")
        onDispose {
            Log.i("TAG", "DisposableEffect:onDispose")
        }
    }
    Text("SubScreen: $count")
}

@Preview
@Composable
fun LifecycleSamplePreview() {
    LifecycleSample()
}

