package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.composebasic.ui.theme.ComposeBasicTheme
import com.example.composebasic.ui.theme.colorUp

class MotionLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    var animateToEnd by remember {
                        mutableStateOf(false)
                    }
                    val progress by animateFloatAsState(
                        targetValue = if (animateToEnd) 1f else 0f,
                        animationSpec = tween(1500)
                    )
                    MotionLayout(
                        motionScene = MotionScene(content = demo),
                        progress = progress,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .layoutId("loginup")
                                .fillMaxHeight()
                                .clickable {
                                    animateToEnd = !animateToEnd
                                }
                                .fillMaxWidth(0.38f)
                                .background(
                                    motionColor(
                                        "loginup",
                                        "background"
                                    )
                                )
                        )
                        Box(
                            modifier = Modifier
                                .clickable {
                                    animateToEnd = !animateToEnd
                                }
                                .clip(RoundedCornerShape(100.dp))
                                .width(120.dp)
                                .height(40.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .layoutId("btnview")
                                .background(Color.Transparent)
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(100.dp))
                                .width(120.dp)
                                .height(40.dp)
                                .border(
                                    width = 1.dp,
                                    color = colorUp,
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .layoutId("btnlogin")
                                .background(colorUp)
                        )
                        CommEditText(
                            modifier = Modifier
                                .layoutId("accountemail")
                                .fillMaxWidth(0.4f),
                            imageVector = Icons.Default.Email,
                            placeholder = "Email"
                        )
                        CommEditText(
                            modifier = Modifier
                                .layoutId("password")
                                .fillMaxWidth(0.4f),
                            imageVector = Icons.Default.Lock,
                            placeholder = "Password"
                        )
                        Image(
                            painter = painterResource(id = R.mipmap.wx),
                            modifier = Modifier
                                .layoutId("wx")
                                .size(30.dp),
                            contentDescription = ""
                        )
                        Image(
                            painter = painterResource(id = R.mipmap.qq),
                            modifier = Modifier
                                .layoutId("qq")
                                .size(30.dp),
                            contentDescription = ""
                        )
                        Image(
                            painter = painterResource(id = R.mipmap.email),
                            modifier = Modifier
                                .layoutId("email")
                                .size(30.dp),
                            contentDescription = "")
                        Text(text = "Forget your password?",
                        modifier = Modifier.layoutId("forget"))
                        Text(
                            text = "Sign in to Diprella",
                            modifier = Modifier.layoutId("logintitlein"),
                            style = MaterialTheme.typography.h5,
                            color = colorUp
                        )
                        Text(
                            text = "Create account",
                            modifier = Modifier.layoutId("logintitleup"),
                            style = MaterialTheme.typography.h5,
                            color = colorUp
                        )
                        Text(
                            text = "Hello,Friend!",
                            modifier = Modifier.layoutId("titleup"),
                            style = MaterialTheme.typography.h5,
                            color = Color.White
                        )
                        Text(
                            text = "Welcome Back!",
                            modifier = Modifier.layoutId("titlein"),
                            style = MaterialTheme.typography.h5,
                            color = Color.White
                        )
                        Text(
                            text = "or use your email account:",
                            modifier = Modifier.layoutId("loginintip"),
                            color = Color(0xFFB0B2B2)
                        )
                        Text(
                            text = "or use your email for registeration:",
                            modifier = Modifier.layoutId("loginuptip"),
                            color = Color(0xFFB0B2B2)
                        )
                        Text(
                            text = "Enter your personal details",
                            modifier = Modifier.layoutId("sub1up"),
                            color = Color.White
                        )
                        Text(
                            text = "To keep connected with us please",
                            modifier = Modifier.layoutId("sub1in"),
                            color = Color.White
                        )
                        Text(
                            text = "and start journey with us",
                            modifier = Modifier.layoutId("sub2up"),
                            color = Color.White
                        )
                        Text(
                            text = "login with your personal info",
                            modifier = Modifier.layoutId("sub2in"),
                            color = Color.White
                        )
                        Text(
                            text = "SIGN UP",
                            modifier = Modifier.layoutId("btnstarttitle"),
                            fontSize = 12.sp,
                        )
                        Text(
                            text = "SIGN IN",
                            modifier = Modifier.layoutId("btnendtitle"),
                            fontSize = 12.sp,
                        )
                        Text(
                            text = "SIGN UP",
                            modifier = Modifier.layoutId("btnup"),
                            fontSize = 12.sp,
                        )
                        Text(
                            text = "SIGN IN",
                            modifier = Modifier.layoutId("btnin"),
                            fontSize = 12.sp,
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeBasicTheme {
        Greeting2("Android")
    }
}