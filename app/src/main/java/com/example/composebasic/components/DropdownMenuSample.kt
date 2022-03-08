package com.example.composebasic.components


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.window.SecureFlagPolicy

@Composable
fun DropdownMenuSample() {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column {
        IconButton(onClick = {
            expanded = !expanded
        }) {
            Icon(imageVector = Icons.Default.PinDrop, contentDescription = null)
        }
        DropdownMenu(
            expanded = expanded, onDismissRequest = {
                Log.i("TAG", "==========")
                expanded = false
            }, offset = DpOffset(x = 10.dp, y = 10.dp),
            properties = PopupProperties(
                focusable = true,
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                securePolicy = SecureFlagPolicy.SecureOn,//设置安全级别，是否可以截屏
            )
        ) {
            DropdownMenuItem(onClick = { expanded = false }) {
                Text(text = "Menu 0")
            }
            DropdownMenuItem(onClick = { expanded = false }) {
                Text(text = "Menu 1")
            }
            DropdownMenuItem(onClick = { expanded = false }) {
                Text(text = "Menu 2")
            }
        }
    }
}

@Preview
@Composable
fun DropdownMenuSamplePreview() {
    DropdownMenuSample()
}

