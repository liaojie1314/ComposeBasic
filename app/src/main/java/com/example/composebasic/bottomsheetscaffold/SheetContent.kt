package com.example.composebasic.bottomsheetscaffold

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SheetContent(onClick:()->Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(80.dp)
                    .clip(CircleShape)
                    .background(Color.Blue)
                    .clickable {
                        onClick()
                    }
            ) {

            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Choose You Favorite Taste",
                color = Color.Black,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Temp",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(80.dp)
                )
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(90.dp),
                    border = BorderStroke(width = 1.dp, color = Color.Blue.copy(alpha = 0.5f))
                ) {
                    Text(text = "warm", color = Color.LightGray)
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(90.dp),
                    border = BorderStroke(width = 1.dp, color = Color.Blue.copy(alpha = 0.5f))
                ) {
                    Text(text = "ice", color = Color.LightGray)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Sugar",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(80.dp)
                )
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(90.dp),
                    border = BorderStroke(width = 1.dp, color = Color.Blue.copy(alpha = 0.5f))
                ) {
                    Text(text = "none", color = Color.LightGray)
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(90.dp),
                    border = BorderStroke(width = 1.dp, color = Color.Blue.copy(alpha = 0.5f))
                ) {
                    Text(text = "little", color = Color.LightGray)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(80.dp)
                )
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(90.dp),
                    border = BorderStroke(width = 1.dp, color = Color.Blue.copy(alpha = 0.5f))
                ) {
                    Text(text = "half", color = Color.LightGray)
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(90.dp),
                    border = BorderStroke(width = 1.dp, color = Color.Blue.copy(alpha = 0.5f))
                ) {
                    Text(text = "full", color = Color.LightGray)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Milk",
                    color = Color.Black,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(80.dp)
                )
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(90.dp),
                    border = BorderStroke(width = 1.dp, color = Color.Blue.copy(alpha = 0.5f))
                ) {
                    Text(text = "soy", color = Color.LightGray)
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(90.dp),
                    border = BorderStroke(width = 1.dp, color = Color.Blue.copy(alpha = 0.5f))
                ) {
                    Text(text = "almond", color = Color.LightGray)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(100)
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "-", color = Color.LightGray)
                    }
                    Text(text = "1", modifier = Modifier.width(20.dp), textAlign = TextAlign.Center)
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "+", color = Color.Black)
                    }
                }
            }
        }
    }
}