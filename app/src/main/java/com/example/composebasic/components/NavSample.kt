package com.example.composebasic.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavSample() {
    val navController = rememberNavController() //获取到导航控制器

    //使用 NavHost 放置页面，进行路由管理
    //startDestination 设置起始显示页面，为了便于管理，可以声明一个静态类来对各个页面的路由名称进行管理
    //在 NavGraphBuilder里使用 Composable 时需要使用composable
    NavHost(
        navController = navController,
        startDestination = Screen.First.route
    ) {

        //嵌套导航结构
        //navigation(startDestination = "username", route = "login") {
        //        composable("username") { ... }
        //        composable("password") { ... }
        //        composable("registration") { ... }
        //    }

        composable(Screen.First.route) {
            FirstScreen {
                navController.navigate("${Screen.Second.route}/11111/22222")
            }
        }

        composable(
            "${Screen.Second.route}/{${Param.SecondScreenParam1.name}}/{${Param.SecondScreenParam2.name}}",
        ) {
            val value1 =
                it.arguments?.getString(Param.SecondScreenParam1.name, "") ?: "Default Value"

            val value2 =
                it.arguments?.getString(Param.SecondScreenParam2.name, "") ?: "Default Value"

            SecondScreen(value1, value2) {
                navController.navigate(Screen.Third.route)
            }
        }

        composable(
            "${Screen.Third.route}?${Param.ThirdScreenParam1.name}={${Param.ThirdScreenParam1.name}}",
            arguments = listOf(
                navArgument(Param.ThirdScreenParam1.name) {
                    defaultValue = "Default Value1"
                })
        ) {
            val value1 =
                it.arguments?.getString(Param.ThirdScreenParam1.name, "") ?: "Default Value2"

            ThirdScreen(value1) {
                navController.popBackStack(Screen.First.route, false)
            }
        }

    }
}

@Composable
fun FirstScreen(onTap: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "First Screen")

        Button(onClick = { onTap() }) {
            Text("Go To Second Screen")
        }
    }
}

@Composable
fun SecondScreen(param1: String, param2: String, onTap: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Second Screen:$param1 and $param2")

        Button(onClick = { onTap() }) {
            Text("Go To Third Screen")
        }
    }
}


@Composable
fun ThirdScreen(param1: String, onTap: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Third Screen:$param1")

        Button(onClick = { onTap() }) {
            Text("Go Back To First Screen")
        }
    }
}

/**
 * 声明一个静态类来对各个页面的路由名称进行管理
 *
 * @property route
 */
sealed class Screen(val route: String) {
    object First : Screen("First")
    object Second : Screen("Second")
    object Third : Screen("Third")
}

sealed class Param(val name: String) {
    object SecondScreenParam1 : Param("param1")
    object SecondScreenParam2 : Param("param2")
    object ThirdScreenParam1 : Param("param1")
}

@Preview
@Composable
fun NavSamplePreview() {
    NavSample()
}

