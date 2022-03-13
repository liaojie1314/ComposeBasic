package com.example.composebasic

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun CommEditText(modifier: Modifier, imageVector: ImageVector, placeholder: String) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = "",
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = "",
                tint = Color(0xFFB0B2B2)
            )
        },
        onValueChange = {},
    placeholder = {
        Text(
            placeholder,
            style = MaterialTheme.typography.body1,
        color = Color(0xFFB0B2B2)
            )
    },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(0xFFEDEDED),
            unfocusedBorderColor = Color(0xFFEDEDED),
            focusedBorderColor = Color(0xFFEDEDED)
        )
    )
}