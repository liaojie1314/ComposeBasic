package com.example.composebasic.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun ConstraintLayoutSample() {
    var isChecked by remember {
        mutableStateOf(false)
    }

    ConstraintLayout(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(Color.Yellow)
    ) {
        val (icon, primaryText, secondlyText, checkBox) = createRefs()

        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = null,
            modifier = Modifier.constrainAs(icon) {
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 8.dp)
                bottom.linkTo(parent.bottom)
            })

        Text(
            "Primary Text",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(primaryText) {
                start.linkTo(icon.end, margin = 8.dp)
                top.linkTo(parent.top)
            })

        Text("secondly text",
            modifier = Modifier.constrainAs(secondlyText) {
                start.linkTo(primaryText.start)
                top.linkTo(primaryText.top)
                bottom.linkTo(parent.bottom)
            })

        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            modifier = Modifier.constrainAs(checkBox) {
                centerVerticallyTo(parent)
                end.linkTo(parent.end)
            })
    }
}

@Composable
fun ConstraintLayoutSample1() {
    var isChecked by remember {
        mutableStateOf(false)
    }

    LazyColumn {
        item {
            ConstraintLayout(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Color.Yellow),
                constraintSet = decoupledConstraints()
            ) {

                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = null,
                    modifier = Modifier.layoutId("icon")
                )

                Text(
                    "Primary Text",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.layoutId("primaryText")
                )

                Text(
                    "secondly text",
                    modifier = Modifier.layoutId("secondlyText")
                )

                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    modifier = Modifier.layoutId("checkBox")
                )
            }
        }
    }
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val icon = createRefFor("icon")
        val primaryText = createRefFor("primaryText")
        val secondlyText = createRefFor("secondlyText")
        val checkBox = createRefFor("checkBox")

        constrain(icon) {
            centerVerticallyTo(parent)
            start.linkTo(parent.start, margin = 8.dp)
        }

        constrain(primaryText) {
            start.linkTo(icon.end, margin = 8.dp)
            top.linkTo(parent.top, margin = 18.dp)
        }

        constrain(secondlyText) {
            start.linkTo(primaryText.start)
            bottom.linkTo(parent.bottom)
            top.linkTo(primaryText.bottom)
        }

        constrain(checkBox) {
            centerVerticallyTo(parent)
            end.linkTo(parent.end)
        }
    }
}


@Preview
@Composable
fun ConstraintLayoutSamplePreview() {
    ConstraintLayoutSample1()
}

