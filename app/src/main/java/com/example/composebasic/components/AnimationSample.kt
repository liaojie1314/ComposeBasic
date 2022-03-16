package com.example.composebasic.components


import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.composebasic.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationSample() {
    var visible by remember {
        mutableStateOf(false)
    }
    Column {
        Button(onClick = {
            visible = !visible
        }) {
            Text("点击")
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically() + expandIn()
        ) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationSample1() {
    var visible by remember {
        mutableStateOf(false)
    }
    Column {
        Button(onClick = {
            visible = !visible
        }) {
            Text("点击")
        }
        AnimatedVisibility(
            visible = visible,
            enter = EnterTransition.None
        ) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.Red)
            ) {
                Box(
                    modifier = Modifier
                        .animateEnterExit(
                            enter = slideInVertically(animationSpec = tween(1000)),
                            exit = slideOutVertically()
                        )
                        .size(150.dp)
                        .background(Color.Green)
                        .align(Alignment.Center)
                )

            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationSample2() {
    var count by remember {
        mutableStateOf(0)
    }
    Row {
        Button(onClick = {
            count++
        }) {
            Text("ADD")
        }
        AnimatedContent(targetState = count) { targetCount ->
            Text("Count: $targetCount")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationSample3() {
    var count by remember {
        mutableStateOf(0)
    }
    Row {
        Button(onClick = {
            count++
        }) {
            Text("+")
        }
        Button(onClick = {
            count--
        }) {
            Text("-")
        }
        AnimatedContent(targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically(initialOffsetY = { fullHeight ->
                        fullHeight
                    }) + fadeIn() with
                            slideOutVertically(targetOffsetY = { fullHeight ->
                                -fullHeight
                            }) + fadeOut()
                } else {
                    slideInVertically(initialOffsetY = { fullHeight ->
                        -fullHeight
                    }) + fadeIn() with
                            slideOutVertically(targetOffsetY = { fullHeight ->
                                fullHeight
                            }) + fadeOut()
                }
            }) { targetCount ->
            Text("Count: $targetCount")
        }
    }
}

@Composable
fun AnimationSample4() {
    var message by remember {
        mutableStateOf("Hello")
    }
    Column {
        Button(onClick = {
            message += " China "
        }) {
            Text("Button")
        }
        Text(text = message, modifier = Modifier.animateContentSize())
    }
}

@Composable
fun AnimationSample5() {
    var size by remember {
        mutableStateOf(30.dp)
    }
    val sizeAnimation by animateDpAsState(
        targetValue = size,
        animationSpec = spring(Spring.DampingRatioHighBouncy)
    )
    Column(
        Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier
                .size(sizeAnimation)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    size += 30.dp
                }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun AnimationSample6() {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colors.primary,
        onClick = { expanded = !expanded }
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }
        ) { targetExpanded ->
            if (targetExpanded) {
                //Expanded()
            } else {
                //ContentIcon()
            }
        }
    }
}

@Preview
@Composable
fun AnimationSamplePreview() {
    AnimationSample6()
}

