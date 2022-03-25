package com.example.composebasic.navigationandbottomappbar


import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composebasic.motionlayoutdemo.ui.theme.Purple200

/**使用
 * Scaffold中bottomBar = { BottomBarView(navController = navController) }
 */

@Composable
fun BottomBarView(navController: NavController) {
    val navItems = listOf(
        BottomItemScreen.Home,
        BottomItemScreen.Item,
        BottomItemScreen.Collecton
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomAppBar {
        navItems.forEach {
            BottomNavigationItem(
                label = { Text(text = it.title) },
                icon = { Icon(imageVector = it.icon, contentDescription = "") },
                selectedContentColor = Purple200,
                unselectedContentColor = Color.White,
                selected = currentRoute == it.route,
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}
