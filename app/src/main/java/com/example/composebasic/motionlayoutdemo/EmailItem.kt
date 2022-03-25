package com.example.composebasic.motionlayoutdemo

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailItem() {
    ListItem(icon = {
        Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
    }, text = {
        Text(text = "Title")
    }, secondaryText = {
        Text(text = "secondaryText")
    })
}