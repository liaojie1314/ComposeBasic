package com.example.composebasic.navigationandbottomappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomItemScreen(val route:String,val title:String,val icon:ImageVector){
    object Home:BottomItemScreen("home","邮件", Icons.Default.Email)
    object Item:BottomItemScreen("item","任务", Icons.Default.List)
    object Collecton:BottomItemScreen("collection","收藏", Icons.Default.Favorite)
}
