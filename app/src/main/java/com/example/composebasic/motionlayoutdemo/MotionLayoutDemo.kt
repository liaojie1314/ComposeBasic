package com.example.composebasic.motionlayoutdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.MotionLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composebasic.motionlayoutdemo.ui.theme.ComposeBasicTheme
import com.example.composebasic.motionlayoutdemo.ui.theme.Purple500
import kotlin.also as also1

class MotionLayoutDemo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val startConstraintSet = ConstraintSet {
                        val topToGuideline = createGuidelineFromTop(120.dp)
                        val bgView = createRefFor("bgview")
                        val navIcon = createRefFor("navicon")
                        val menuIcon = createRefFor("menuicon")
                        val title = createRefFor("title")
                        val searchText = createRefFor("searchtext")
                        val lazyColumn = createRefFor("list")
                        constrain(bgView) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(topToGuideline)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        }
                        constrain(navIcon) {
                            start.linkTo(bgView.start, 10.dp)
                            top.linkTo(bgView.top, 10.dp)
                        }
                        constrain(menuIcon) {
                            end.linkTo(bgView.end, 10.dp)
                            top.linkTo(bgView.top, 10.dp)
                        }
                        constrain(title) {
                            top.linkTo(navIcon.top)
                            bottom.linkTo(navIcon.bottom)
                            start.linkTo(navIcon.start, 10.dp)
                        }
                        constrain(searchText) {
                            start.linkTo(bgView.start, 8.dp)
                            end.linkTo(bgView.end, 8.dp)
                            top.linkTo(navIcon.bottom)
                            bottom.linkTo(topToGuideline)
                            width = Dimension.fillToConstraints
                            height = Dimension.value(42.dp)
                        }
                        constrain(lazyColumn) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(topToGuideline)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        }
                    }
                    val endConstraintSet = ConstraintSet {
                        val topToGuideline = createGuidelineFromTop(60.dp)
                        val bgView = createRefFor("bgview")
                        val navIcon = createRefFor("navicon")
                        val menuIcon = createRefFor("menuicon")
                        val title = createRefFor("title")
                        val searchText = createRefFor("searchtext")
                        val lazyColumn = createRefFor("list")
                        constrain(bgView) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(topToGuideline)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        }
                        constrain(navIcon) {
                            start.linkTo(bgView.start, 10.dp)
                            top.linkTo(bgView.top, 10.dp)
                        }
                        constrain(menuIcon) {
                            end.linkTo(bgView.end, 10.dp)
                            top.linkTo(bgView.top, 10.dp)
                        }
                        constrain(title) {
                            bottom.linkTo(parent.top)
                            start.linkTo(navIcon.end, 10.dp)
                        }
                        constrain(searchText) {
                            start.linkTo(navIcon.end, 10.dp)
                            end.linkTo(menuIcon.start, 10.dp)
                            top.linkTo(navIcon.top)
                            bottom.linkTo(navIcon.bottom)
                            width = Dimension.fillToConstraints
                            height = Dimension.value(34.dp)
                        }
                        constrain(lazyColumn) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(topToGuideline)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        }
                    }
                    var animateToEnd by remember {
                        mutableStateOf(false)
                    }
                    val progress by animateFloatAsState(
                        targetValue = if (animateToEnd) 1f else 0f,
                        animationSpec = tween(800)
                    )
                    val state = rememberLazyListState()
                    val viewModel: ScrollViewModel = viewModel()
                    val scrollUpState = viewModel.scrollUp.observeAsState()
                    viewModel.updateScrollPosition(state.firstVisibleItemIndex)
                    MotionLayout(
                        start = startConstraintSet,
                        end = endConstraintSet,
                        progress = progress,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .layoutId("bgview")
                                .background(Purple500)
                        )
                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .layoutId("navicon")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "",
                                tint = Color.Black
                            )
                        }
                        Text(
                            text = "MotionLayout",
                            modifier = Modifier.layoutId("title")
                        )
                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.layoutId("menuicon")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add, contentDescription = "",
                                tint = Color.Black
                            )
                        }
                        OutlinedTextField(
                            value = "", onValueChange = {},
                            leadingIcon = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        imageVector = Icons.Default.Search, contentDescription = "",
                                        tint = Color.LightGray
                                    )
                                }
                            },
                            shape = RoundedCornerShape(2.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White
                            ),
                            modifier = Modifier
                                .layoutId("searchtext")
                                .background(Color.White)
                        )
                        LazyColumn(
                            modifier = Modifier.layoutId("list"),
                            contentPadding = PaddingValues(10.dp),
                            state = state,
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(20) {
                                EmailItem()
                            }
                        }
                        if (scrollUpState.value==true){

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeBasicTheme {
        Greeting3("Android")
    }
}