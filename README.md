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
参数¶
color 设置字体颜色¶
```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!", color = Color.Red)
}
```
