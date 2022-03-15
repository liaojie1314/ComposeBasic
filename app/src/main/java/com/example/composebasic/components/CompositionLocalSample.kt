package com.example.composebasic.components


import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun CompositionLocalSample() {
    val navController = rememberNavController()
    val user = User("Test")
    CompositionLocalProvider(localActiveUser provides user) {
        NavHost(navController = navController, startDestination = "Home") {
            composable("Home") {
                HomeScreen {
                    navController.navigate("Detail")
                }
            }
            composable("Detail") {
                DetailScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(onTap: () -> Unit) {
    Column {
        Text(text = "HomeScreen:${localActiveUser.current.name}")
        Button(onClick = { onTap() }) {
            Text(text = "Navigate to Detail")
        }
    }
}

@Composable
fun DetailScreen() {
    Text(text = "DetailScreen:${localActiveUser.current.name}")
}

val localActiveUser = compositionLocalOf<User> { error("user is null") }

data class User(val name: String)

@Preview
@Composable
fun CompositionLocalSamplePreview() {
    CompositionLocalSample()
}

