package com.example.composebasic.components


import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnSample() {
    val data = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
        13, 14, 15, 16, 17, 18, 19, 20
    )
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        data.forEach {
            ListItem(icon = {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
            }, text = {
                Text(text = "Title $it")
            }, secondaryText = {
                Text(text = "secondaryText")
            }, modifier = Modifier.clickable {
                coroutineScope.launch {
                    scrollState.scrollTo(scrollState.maxValue)//scrollTo直接跳到某个位置
                    // scrollState.scrollBy(100f)
                    // scrollBy 从当前地方跳到100 跳到100后在往后面跳100
                }
            })
            DisposableEffect(Unit) {
                Log.d("TAG", "effect : $it")
                onDispose {
                    Log.d("TAG", "effect : $it")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun LazyColumnSample1() {
    val data = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
        13, 14, 15, 16, 17, 18, 19, 20
    )
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(state = lazyListState) {
        stickyHeader {
            Text(
                text = "Sticky Header",
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

        }
//        item {
//            Text(text = "Item 0")
//        }
        items(data) {
            ListItem(icon = {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
            }, text = {
                Text(text = "Title $it")
            }, secondaryText = {
                Text(text = "secondaryText")
            }, modifier = Modifier.clickable {
                coroutineScope.launch {
                    lazyListState.scrollToItem(data.size - 1)
                }
            })
            DisposableEffect(Unit) {
                Log.d("TAG", "effect : $it")
                onDispose {
                    Log.d("TAG", "effect : $it")
                }
            }
        }
    }
}

@Preview
@Composable
fun LazyColumnSamplePreview() {
    LazyColumnSample1()
}

