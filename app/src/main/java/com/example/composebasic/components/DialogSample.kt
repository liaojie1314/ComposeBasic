package com.example.composebasic.components


import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DialogSample() {
    var showDialog by remember {
        mutableStateOf(false)
    }
    Column {
        Button(onClick = {
            showDialog = true
        }) {
            Text("show dialog")
        }
        if (showDialog) {
//            Dialog(onDismissRequest = {
//                showDialog = false
//            }) {
//                Surface(
//                    color = Color.White,
//                    modifier = Modifier.size(200.dp, 100.dp)
//                ) {
//                    Column {
//                        Text("Dialog Content")
//                    }
//                }
//            }
            AlertDialog(onDismissRequest = {
                showDialog = false
            },
                title = {
                    Text("Title")
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                    }) {
                        Text("Confirm Button")
                    }
                }, dismissButton = {
                    TextButton(onClick = {
                        showDialog = false
                    }) {
                        Text("Cancel Button")
                    }
                },
                text = {
                    Text("这是Dialog的内容")
                })
        }
    }
}

@Preview
@Composable
fun DialogSamplePreview() {
    DialogSample()
}

