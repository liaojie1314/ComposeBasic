package com.example.composebasic.components

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

//@OptIn(ExperimentalUnitApi::class)
@Composable
fun TextSample() {
//    Text(text = "hello text")
    val annotatedString = buildAnnotatedString {
        append("点击登录代表你同意")
        //往字符串中添加注解 tag 为标识 直到遇到 pop
        pushStringAnnotation("protocol", "https://www.baidu.com")
        withStyle(style = SpanStyle(Color.Blue, textDecoration = TextDecoration.Underline)) {
            append("用户协议")
        }
        pop()
        append("和")
        pushStringAnnotation("privacy", "https://www.baidu.com")
        withStyle(style = SpanStyle(Color.Blue, textDecoration = TextDecoration.Underline)) {
            append("隐私协议")
        }
        pop()
    }
    //长按复制
    /*
    SelectionContainer {
        Text(
            text = stringResource(id = R.string.text),
            color = Color.Blue,//颜色
            //fontSize = TextUnit(16f, TextUnitType.Sp)
            fontSize = 16f.sp,//文字大小
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Default,//字体
            letterSpacing = 10.sp,
            //textDecoration = TextDecoration.LineThrough,//删除线
            textDecoration = TextDecoration.combine(
                listOf(
                    TextDecoration.Underline,
                    TextDecoration.LineThrough
                )
            ),
            textAlign = TextAlign.Center,//居中
            lineHeight = 30.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle()
        )
    }
     */
    //文字点击处理 索引从0开始
    ClickableText(text = annotatedString, onClick = { offset ->
        //从字符串中根据 tag 查找注解
        annotatedString.getStringAnnotations("protocol", start = offset, end = offset).firstOrNull()
            ?.let { annotation ->
                Log.d("TAG", "你点击了${annotation.item}")
            }
        annotatedString.getStringAnnotations("privacy", start = offset, end = offset).firstOrNull()
            ?.let { annotation ->
                Log.d("TAG", "你点击了${annotation.item}")
            }
    })
}

@Preview
@Composable
fun TextSamplePreview() {
    TextSample()
}