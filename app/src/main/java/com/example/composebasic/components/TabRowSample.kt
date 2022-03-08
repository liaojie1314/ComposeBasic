package com.example.composebasic.components


import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TabRowSample() {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    Column {
        TabRow(
            selectedTabIndex = selectedIndex,
            contentColor = Color.Yellow,
            indicator = {}
        ) {
            LeadingIconTab(
                selected = selectedIndex == 0,
                text = {
                    Text(text = "Tab0")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = null
                    )
                },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.White,
                onClick = { selectedIndex = 0 })
            Tab(selected = selectedIndex == 1, text = {
                Text(text = "Tab1")
            }, onClick = { selectedIndex = 1 })
            Tab(selected = selectedIndex == 2, text = {
                Text(text = "Tab2")
            }, onClick = { selectedIndex = 2 })
        }
        Text("current index : $selectedIndex")
    }
}

@Preview
@Composable
fun TabRowSamplePreview() {
    TabRowSample()
}

