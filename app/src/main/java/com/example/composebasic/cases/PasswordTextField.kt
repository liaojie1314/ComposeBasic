package com.example.composebasic.cases


import android.content.Context
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasic.R

@Composable
fun PasswordTextField(context: Context) {
    val textValue = remember {
        mutableStateOf("")
    }
    val pwdOff = remember{
        mutableStateOf(false)
    }
    val icon = if (pwdOff.value)
        painterResource(id = R.mipmap.password_on)
    else
        painterResource(id = R.mipmap.password_off)
    OutlinedTextField(
        value = textValue.value,
        label = { Text(text = "密码") },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
        trailingIcon = {
            IconButton(onClick = {
                pwdOff.value=!pwdOff.value
            }) {
                Icon(icon, contentDescription ="")
            }
        },
        visualTransformation = if (pwdOff.value) VisualTransformation.None
        else PasswordVisualTransformation(),
        placeholder = { Text(text = "请输入密码") },
        onValueChange = {
            textValue.value = it
        }
    )
}

