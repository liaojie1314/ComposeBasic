package com.example.composebasic.bottomsheetscaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.bottomsheetscaffold.ui.theme.ComposeBasicTheme
import kotlinx.coroutines.launch

class BottomSheetScaffoldActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val scope = rememberCoroutineScope()
                    val bottomSheetScaffoldState= rememberBottomSheetScaffoldState(
                        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
                    )
                    BottomSheetScaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = bottomSheetScaffoldState,
                        sheetContent = {
                            Box(modifier = Modifier.fillMaxHeight(0.75f)){
                                SheetContent {
                                    scope.launch {
                                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed){
                                            bottomSheetScaffoldState.bottomSheetState.expand()
                                        }else{
                                            bottomSheetScaffoldState.bottomSheetState.collapse()
                                        }
                                    }
                                }
                            }
                        },
                        sheetContentColor = Color.White,
                        sheetPeekHeight = 20.dp,
                        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    ) {
                        ContentCompose()
                    }
                }
            }
        }
    }
}
