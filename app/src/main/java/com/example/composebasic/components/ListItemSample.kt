package com.example.composebasic.components


import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListItemSample() {
    var list by remember {
        mutableStateOf(
            listOf(
                false,
                false,
                false,
                false,
                false
            )
        )
    }
    Column {
        list.forEachIndexed { rawIndex, listItem ->
            ListItem(icon = {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = null
                )
            }, text = {
                Text(text = "Title $rawIndex")
            }, secondaryText = {
                Text(text = "Second Text")
            }, trailing = {
                Checkbox(checked = listItem, onCheckedChange = {
                    list = list.mapIndexed { newIndex, listItem ->
                        if (rawIndex == newIndex) {
                            !listItem
                        } else {
                            listItem
                        }
                    }
                })
            })
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListItemSample1() {
    var list by remember {
        mutableStateOf(
            listOf(
                ListItem("Item 0", false),
                ListItem("Item 1", false),
                ListItem("Item 2", false),
                ListItem("Item 3", false),
                ListItem("Item 4", false),
            )
        )
    }
    Column {
        list.forEachIndexed { rawIndex, listItem ->
            ListItem(icon = {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = null
                )
            }, text = {
                Text(text = "Title $rawIndex")
            }, secondaryText = {
                Text(text = "Second Text")
            }, trailing = {
                Checkbox(checked = listItem.checked, onCheckedChange = {
                    list = list.mapIndexed { newIndex, listItem ->
                        val newItem = listItem.copy()
                        if (rawIndex == newIndex) {
                            newItem.checked = !listItem.checked
                        } else {
                            newItem.checked = listItem.checked
                        }
                        return@mapIndexed newItem
                    }
                })
            })
        }
    }
}

data class ListItem(val title: String, var checked: Boolean)

@Preview
@Composable
fun ListItemSamplePreview() {
    ListItemSample1()
}

