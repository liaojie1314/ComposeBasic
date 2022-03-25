package com.example.composebasic.cases.loginpage


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun MyTextField(
    value: String,
    colors: TextFieldColors,
    trailingIcon: ImageVector,
    trailingtintIcon: Color,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        colors = colors,
        trailingIcon = {
            Icon(
                imageVector = trailingIcon, contentDescription = "",
                tint = trailingtintIcon
            )
        },
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        singleLine = true,
        onValueChange = onValueChange
    )
}

