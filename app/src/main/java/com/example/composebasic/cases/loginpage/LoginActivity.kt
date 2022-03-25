package com.example.composebasic.cases.loginpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasic.R
import com.example.composebasic.cases.loginpage.ui.theme.ComposeBasicTheme
import com.example.composebasic.cases.loginpage.ui.theme.color1
import com.example.composebasic.cases.loginpage.ui.theme.color2
import com.example.composebasic.cases.loginpage.ui.theme.color3

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    //add Box
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        //add Card
                        Card(
                            modifier = Modifier.background(color = Color.White),
                            elevation = 8.dp,
                            shape = MaterialTheme.shapes.medium
                        ) {
                            //add Column
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(12.dp)
                            ) {
                                val emailText = remember {
                                    mutableStateOf("")
                                }
                                val passwordText = remember {
                                    mutableStateOf("")
                                }
                                val colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = color1,
                                    unfocusedBorderColor = color2,
                                    cursorColor = color3
                                )
                                Image(
                                    painter = painterResource(id = R.mipmap.login),
                                    contentDescription = ""
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "电子邮件",
                                    color = color1,
                                    modifier = Modifier.fillMaxWidth(0.85f),
                                    textAlign = TextAlign.Left
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                //添加文本框
                                MyTextField(
                                    value = emailText.value,
                                    colors = colors,
                                    trailingIcon = Icons.Default.Email,
                                    trailingtintIcon = color1,
                                    modifier = Modifier.fillMaxWidth(0.85f),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Email
                                    ),
                                    onValueChange = {
                                        emailText.value = it
                                    }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "密码",
                                    color = color1,
                                    modifier = Modifier.fillMaxWidth(0.85f),
                                    textAlign = TextAlign.Left
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                MyTextField(
                                    value = passwordText.value,
                                    colors = colors,
                                    trailingIcon = Icons.Default.Lock,
                                    trailingtintIcon = color1,
                                    modifier = Modifier.fillMaxWidth(0.85f),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Password
                                    ),
                                    visualTransformation = PasswordVisualTransformation(),
                                    onValueChange = {
                                        passwordText.value = it
                                    }
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                //add Button
                                //Button 添加动画
                                AnimatedButton(emailText, passwordText)
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(text = "忘记密码", color = color2, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting4(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeBasicTheme {
        Greeting4("Android")
    }
}