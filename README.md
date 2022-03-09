[资料来源:https://docs.bughub.icu/compose](https://docs.bughub.icu/compose)

# Jetpack Compose 是什么？

Jetpack Compose 是用于构建原生 Android 界面的新工具包。它可简化并加快 Android 上的界面开发，使用更少的代码、强大的工具和直观的 Kotlin API，快速让应用生动而精彩。

# 环境

Android Studio一定要确定版本是Arctic Fox | 2020.3.1之后的版本（包含）

# 简单组件

## Text

Text 文本显示控件

```kotlin
@Composable
fun Text(
    text: String?,
    modifier: Modifier? = Modifier,
    color: Color? = Color.Unspecified,
    fontSize: TextUnit? = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit? = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit? = TextUnit.Unspecified,
    overflow: TextOverflow? = TextOverflow.Clip,
    softWrap: Boolean? = true,
    maxLines: Int? = Int.MAX_VALUE,
    onTextLayout: ((TextLayoutResult) -> Unit)? = {},
    style: TextStyle? = LocalTextStyle.current
): Unit
```

用法:

直接显示

```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!")
}
```

从 res 中读取文字显示

```kotlin
@Composable
fun TextSample() {
    Text(text = stringResource(R.string.content))
}

<resources>
    <string name="content">你好，世界!</string>
</resources>
```

参数
color 设置字体颜色

```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!", color = Color.Red)
}
```

fontSize 设置文字大小

fontSize 默认是跟随父级文字大小。

接收的是一个 TextUnit，可以设置 SP(像素值) 和 EM(字体值) 单位的值

以下示例是直接使用 TextUnit 创建对象进行赋值，因为这个构造函数还是实验性的，随时都可能有改动或删除，因此需要在函数前增加@OptIn(ExperimentalUnitApi::class)注解

```kotlin
@OptIn(ExperimentalUnitApi::class)
@Composable
fun TextSample() {
    Text(text = "Hello World!", fontSize = TextUnit(16f, TextUnitType.Sp))
}
```

当然，系统对 Int 、Double 和 Float 三种类型进行了扩展，可以直接按照下面的方式进行使用

```kotlin
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.em

@Composable
fun TextSample1() {
    Text(text = "Hello World!", fontSize = 16.0.sp))
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun TextSample2() {
    Text(text = "Hello World!", fontSize = 16.em))
}
```

fontStyle 设置文字样式

+ FontStyle.Italic 设置为斜体
+ FontStyle.Normal 设置为正常体(默认状态)

```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!", fontStyle = FontStyle.Italic))
}
```

fontWeight 设置文字比重

系统预设了很多比重值可以直接使用，例如 FontWeight.Bold ，也可以使用 FontWeight(100)
fontFamily 设置文字字体

同样系统也预设了几个字体供选择使用，例如 FontFamily.SansSerif。也可以加载到res 下的字体文件

```kotlin
@Composable
fun TextSample() {

    val firaSansFamily = FontFamily(
        Font(R.font.firasans_light, FontWeight.Light),
        Font(R.font.firasans_regular, FontWeight.Normal),
        Font(R.font.firasans_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.firasans_medium, FontWeight.Medium),
        Font(R.font.firasans_bold, FontWeight.Bold)
    )

    Text(text = "Hello World!", fontFamily = firaSansFamily))
}
```
letterSpacing 设置字符间距

```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!", letterSpacing = 15.sp))
}
```

textDecoration 设置文字装饰

+ TextDecoration.None 无装饰(默认)
+ TextDecoration.Underline 下划线
+ TextDecoration.LineThrough 删除线

还可以通过TextDecoration.combine()合并使用多种装饰

```kotlin
@Composable
fun TextSample() {
    Text(
        text = "Hello World!",
        textDecoration = TextDecoration.combine(
            listOf(
                TextDecoration.LineThrough,
                TextDecoration.Underline
            )
        )
    )
}
```

textAlign 设置文本对齐方式

需要固定宽度，才有效果

+ TextAlign.Center
+ TextAlign.End
+ TextAlign.Justify

lineHeight 设置文本行高

```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!", lineHeight = 15.sp))
}
```

overflow 设置文本超出时如何显示

+ TextOverflow.Ellipsis 以省略号显示
+ TextOverflow.Clip 裁剪
+ TextOverflow.Visible 尽可能显示

maxLines 文本显示行数

```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!", maxLines = 1))
}
```

style 样式

上面讲到的大部分文字修饰，都可以直接通过 TextStyle 进行修饰，除此之外还多出几个样式

+ fontFeatureSettings字体的高级设置，类似 CSS的font-feature-settings，可以参考https://www.w3.org/TR/css-fonts-3/#font-feature-settings-prop
+ background 设置背景颜色
+ shadow 设置阴影
+ textIndent 设置首先缩进

```kotlin
@Composable
fun TextSample() {
    Text(
        text = "锄禾日当午，汗滴禾下土。谁知盘中餐，粒粒皆辛苦",
        modifier = Modifier.width(110.dp),
        style = TextStyle(
            background = Color.White,
            shadow = Shadow(
                color = Color.Red,
                offset = Offset(5f, 5f),
                blurRadius = 10f
            ),
            textIndent = TextIndent(20.sp)
        )
    )
}
```

SelectionContainer 文字复制

默认情况下 Text 并不能进行复制等操作，我们需要设置 SelectionContainer 来包装 Text

```kotlin
@Composable
fun TextSample() {
    SelectionContainer(
        Text(
            text = "锄禾日当午，汗滴禾下土。谁知盘中餐，粒粒皆辛苦"
        )
    )
}
```

Text 语句中设置不同样式

如果想让一个 Text 语句中有不同的样式，需要使用到 AnnotaedString

AnnotaedString 是一个数据类，包含文本，以及多种样式

```kotlin
@Composable
fun TextSample() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(Color.Red)) {
                append("锄禾日当午，")
            }
            withStyle(style = SpanStyle(Color.Green)) {
                append("汗滴禾下土。")
            }
            withStyle(style = SpanStyle(Color.Blue)) {
                append("谁知盘中餐，")
            }
            withStyle(style = SpanStyle(Color.Yellow)) {
                append("粒粒皆辛苦")
            }
        }
    )
}
```

ClickableText文本点击控件

想要让文本可以接收到点击事件，可以使用 ClickableText，控件带有一个 onClick 参数，参数回调中还可以知道当前点击字条的 offset 是多少
简单用法

```kotlin
@Composable
fun TextSample() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(Color.Red)) {
                append("锄禾日当午，")
            }
            withStyle(style = SpanStyle(Color.Green)) {
                append("汗滴禾下土。")
            }
            withStyle(style = SpanStyle(Color.Blue)) {
                append("谁知盘中餐，")
            }
            withStyle(style = SpanStyle(Color.Yellow)) {
                append("粒粒皆辛苦")
            }
        }, onClick = { offset ->
            Log.d("TextSample", "offset:$offset")
        }
    )
}
```

高级用法

从上面 设置不同样式 和 文本点击 我们知道了如何在 Text 语句内设置不同的样式，也知道了如何获得点击的文字，那我们是不是可以实现在文本内设置部分文字可以点击呢！

比如『点击登录代表您知悉和用户协议和隐私政策』

```
@Composable
fun TextSample() {
    val annotatedString = buildAnnotatedString {
        append("点击登录代表您知悉和同意")

        //往字符串中添加一个注解，直到遇到 pop() 。tag 为注解标识，annotation 为传递内容
        pushStringAnnotation("protocol", annotation = "https://docs.bughub.icu/compose")
        withStyle(style = SpanStyle(Color.Blue)) {
            append("用户协议")
        }
        pop()

        append("和")

        pushStringAnnotation("privacy", annotation = "https://randywei.gitee.com")
        withStyle(style = SpanStyle(Color.Blue)) {
            append("隐私政策")
        }
        pop()
    }

    ClickableText(
        annotatedString, onClick = { offset ->
            //从字符串中查找注解
            annotatedString.getStringAnnotations("protocol", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    Log.d("TextSample", "点击了用户协议：${annotation.item}")
                }

            annotatedString.getStringAnnotations("privacy", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    Log.d("TextSample", "点击了隐私政策：${annotation.item}")
                }
        }
    )
}
```
