package com.example.composebasic.bottomsheetscaffold


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.R

@Composable
fun ContentCompose() {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(5f)) {
            Image(
                painter = painterResource(id = R.mipmap.coffee),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue
                    ),
                    shape = RoundedCornerShape(26)
                ) {
                    Text(text = "New Product", color = Color.White)
                }
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Kopi Luwak",
                    color = Color.White,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Indonesia",
                    color = Color.White,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Chido coffee is a kind of milk coffee.",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "It first mixes milk with vanilla...",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.body2
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(3f)
                .padding(20.dp)
        ) {
            Text(
                text = "Kopi Luwak",
                color = Color.Black,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Row {
                Text(
                    text = "$",
                    color = Color.Gray,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "20",
                    color = Color.Blue,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Chido coffee is a kind of milk coffee.",
                color = Color.LightGray,
                style = MaterialTheme.typography.body2
            )
            Text(
                text = "It first mixes milk with vanilla...",
                color = Color.LightGray,
                style = MaterialTheme.typography.body2
            )
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd){
                FloatingActionButton(onClick = { /*TODO*/ },
                backgroundColor = Color.Blue) {
                    Icon(Icons.Default.Add, contentDescription = "",tint=Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
fun ContentComposePreview() {
    ContentCompose()
}

