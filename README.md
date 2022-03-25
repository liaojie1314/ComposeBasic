[资料来源:https://docs.bughub.icu/compose](https://docs.bughub.icu/compose)

# Jetpack Compose 是什么？

Jetpack Compose 是用于构建原生 Android 界面的新工具包。它可简化并加快 Android 上的界面开发，使用更少的代码、强大的工具和直观的 Kotlin API，快速让应用生动而精彩。

# 环境

Android Studio一定要确定版本是Arctic Fox | 2020.3.1之后的版本（包含）

# 组件

## Text

### Text 文本显示控件

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

### 用法:

#### 直接显示

```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!")
}
```

#### 从 res 中读取文字显示

```kotlin
@Composable
fun TextSample() {
    Text(text = stringResource(R.string.content))
}

<resources>
    <string name="content">你好，世界!</string>
</resources>
```

##### 参数
+ color 设置字体颜色

```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!", color = Color.Red)
}
```

+ fontSize 设置文字大小

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

+ fontStyle 设置文字样式

+ FontStyle.Italic 设置为斜体
+ FontStyle.Normal 设置为正常体(默认状态)

```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!", fontStyle = FontStyle.Italic))
}
```

+ fontWeight 设置文字比重

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
+ letterSpacing 设置字符间距

```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!", letterSpacing = 15.sp))
}
```

+ textDecoration 设置文字装饰
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

+ textAlign 设置文本对齐方式

需要固定宽度，才有效果

	+ TextAlign.Center

- TextAlign.End
- TextAlign.Justify

- lineHeight 设置文本行高

```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!", lineHeight = 15.sp))
}
```

+ overflow 设置文本超出时如何显示

+ TextOverflow.Ellipsis 以省略号显示
+ TextOverflow.Clip 裁剪
+ TextOverflow.Visible 尽可能显示

+ maxLines 文本显示行数

```kotlin
@Composable
fun TextSample() {
    Text(text = "Hello World!", maxLines = 1))
}
```

+ style 样式

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

+ SelectionContainer 文字复制

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

+ Text 语句中设置不同样式

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

+ ClickableText文本点击控件

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

#### 高级用法

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

## Button

##### 属性

```kotlin
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
): Unit
```

### 基本用法

```kotlin
@Composable
fun ButtonSample() {
    Button(
        onClick = {
            Log.d("ButtonSample", "click the button")
        },
    ) {
        Text(text = "这里有一个按钮")
    }
}
```

##### 参数

+ enabled 是否启用或禁用
+ elevation 投影
+ border 边框线

```kotlin
@Composable
fun ButtonSample() {
    Button(
        onClick = {
            Log.d("ButtonSample", "click the button")
        },
        border = BorderStroke(1.dp,Color.Red)
    ) {
        Text(text = "这里有一个按钮")
    }
}
```

+ colors设置颜色，可以设置背景颜色、前景颜色、禁用状态和启动状态下的颜色

```kotlin
@Composable
fun ButtonSample() {
    Button(
        onClick = {
            Log.d("ButtonSample", "click the button")
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Yellow,
            contentColor = Color.Green
        )
    ) {
        Text(text = "这里有一个按钮")
    }
}
```

+ contentPadding 内容内间距

### TextButton

TextButton一般是用来显示文字按钮的

```kotlin
@Composable
fun ButtonSample() {
    TextButton(
        onClick = {
            Log.d("ButtonSample", "click the button")
        },
    ) {
        Text(text = "TextButton")
    }
}
```

### OutlinedButton

```kotlin
@Composable
fun ButtonSample() {
    OutlinedButton(
        onClick = {
            Log.d("ButtonSample", "click the button")
        },
    ) {
        Text(text = "OutlinedButton")
    }
}
```

### IconButton

用来显示图标按钮

```kotlin
@Composable
fun ButtonSample() {
    IconButton(
        onClick = {
            Log.d("ButtonSample", "click the button")
        },
    ) {
        Icon(imageVector = Icons.Default.Stairs, contentDescription = null)
    }
}
```

## Icon

##### 属性

```kotlin
@Composable
fun Icon(
    imageVector: ImageVector,//bitmap: ImageBitmap,painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
)
```

### 用法

#### 用法一

可以直接引用官方的图标库 ，例如：Icons.Default.AccountBox

```kotlin
@Composable
fun IconSample() {
    Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
}
```

在官方网站上，我们看到提供的图标库中，有些可能无法正常显示。是因为默认 SDK 中只是包含部分图标，如果需要使用更多图标需要引入扩展库

> implementation "androidx.compose.material:material-icons-extended:$compose_version" 

#### 用法二

可以使用 drawble 里面的图片

```kotlin
@Composable
fun IconSample() {
  Icon(
    painter = painterResource(id = R.drawable.ic_android_black_24dp),
    contentDescription = null,
    tint = Color.Blue
  )
}
```

#### 用法三

可以引用 ImageBitmap

```kotlin
@Composable
fun IconSample() {
    var bitmap:ImageBitmap ? = null
    with(LocalContext.current){
         bitmap = ImageBitmap.imageResource(resources,R.drawable.newbanner4)
    }
    bitmap?.let { Icon(bitmap = it, contentDescription = null) }
}
```

##### 参数

+ tint 设置图标颜色

```kotlin
@Composable
fun IconSample() {
    Icon(imageVector = Icons.Default.Deck, contentDescription = null, tint = Color.Red)
}
```

## Image

##### 属性

```kotlin
@Composable
fun Image(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
): Unit
```

图片跟 Icon 差不多也可以通过三种方式引入图片，本页只展示一种方式

##### 参数

+ contentScale 设置图片的伸展方式：ContentScale.Inside、ContentScale.Crop 等
+ colorFilter 设置颜色滤镜

```kotlin
@Composable
fun ImageSample() {
    Image(
        painter = painterResource(id = R.drawable.newbanner4),
        contentDescription = null,
        contentScale = ContentScale.Inside,
        colorFilter = ColorFilter.tint(Color.Red, blendMode = BlendMode.Color)
    )
}
```

## Modifiers

Modifiers 可以修饰一个 composable。

在前面基础组件中，可能或多或少见过 Modifier，但不知道它是干什么的，那么这一节我们来看看 Modifier 到底能做哪些事情呢！

+ 改变 composable 的尺寸、布局、动作和外观
+ 添加信息，比如无障碍辅助信息
+ 处理用户输入
+ 增加高级交互，比如点击、滚动、拖动或缩放等等

```kotlin
@Composable
fun TextSample() {
    Text(
        text = "Hello World!",
        modifier = Modifier
            .background(Color.Red)
            .padding(8.dp),
        style = TextStyle(background = Color.Green)
    )
}
```

在上面的代码中，我们给文本添加了绿色背景，然后通过 Modifier 设置了两个修饰

+ background 设置控件的背景颜色
+ padding 设置内间距

### modifiers 的先后顺序

修饰的先后顺序是会影响到修饰效果的。比如上面的例子中，如果把 padding 和 background 的顺序调换一下，红色背景将不会显示出来。

#### 内置的 Modifiers

系统内置了一些 modifiers 供用户使用

+ padding 和 size

通常情况下布局会根据其内容大小来进行显示。但是你可以通过 size 来控制布局的大小

```kotlin
@Composable
fun ArtistCard(/*...*/) {
    Row(
        modifier = Modifier.size(width = 400.dp, height = 100.dp)
    ) {
        Image(/*...*/)
        Column { /*...*/ }
    }
}
```

> Warning

> 如果指定的大小不满足父布局的约束，则尺寸将会无效。如果强制设置请使用而不考虑父控件约束，可以使用 requiredSize

```kotlin
@Composable
fun ImageSample() {
    Column {
        //父控件设置尺寸为100dp
        Column(modifier = Modifier.size(100.dp)) {
            Image(
                painter = painterResource(id = R.drawable.newbanner4),
                contentDescription = null,
                modifier = Modifier.size(150.dp),//此时子控件使用 size 设置150dp 是无效的
                colorFilter = ColorFilter.tint(Color.Red, blendMode = BlendMode.Color)
            )
        }
        //父控件设置尺寸为100dp
        Column(modifier = Modifier.size(100.dp)) {
            Image(
                painter = painterResource(id = R.drawable.newbanner4),
                contentDescription = null,
                modifier = Modifier.requiredSize(150.dp),//此时子控件需要使用 requiredSize 设置为150dp才有效
                colorFilter = ColorFilter.tint(Color.Red, blendMode = BlendMode.Color)
            )
        }
    }
}
```

上面的图片是父布局控制为100dp，图片设置150dp 无效。下面的图片使用 requiredSize 设置150dp 后就有效了。

+ fillMaxWidth

如果你想让填满父布局，可以使用 fillMaxWidth 和 fillMaxHeight 或直接使用 fillMaxSize

+ offset

如果想要设置组件基于现在位置的偏移，可以通过 Modifier.offset()来设置 x 和 y 值

#### Modifier作用域

在 Compose 中，modifer 是存在作用域的，有一些modifier 只能在特定组件中才能使用。比如 matchParentSize 只能在 BoxScope 中使用。

[Modifiers 列表](https://docs.bughub.icu/compose/ModifiersList)

[官方 Modifiers 介绍](https://developer.android.com/jetpack/compose/modifiers)

[Full list of modifiers](https://developer.android.com/jetpack/compose/modifiers-list)

## 状态

应用中的状态是指可以随时间变化的任何值。这个定义很广泛包括数据库或类中变量的所有内容。

+ 当网速不通畅时需要显示一个 Snackbar 给用户
+ 博文和相关评论发生变化时
+ 用户点击按钮发生的动画

Jetpack Compose 可帮助您明确状态在 Android 应用中的存储位置和使用方式。本指南重点介绍状态与可组合项之间的关联，以及 Jetpack Compose 提供的 API，您可以通过这些 API 更轻松地处理状态。

### 状态和composition

由于Compose 是声明式的，所以当需要改变其任何内容的时候，通过设置新的参数调用同一组声明，这些参数就是 UI 的表现形式。每State 更新时，都会发生重组。

### composable 中的状态

Composable中可以使用`remember`来记住单个对象。系统会在初始化由 `remember`计算的值存储在Composable中，并在重组的时候返回存储的值。`remember`既可以存储可变对象，也可以存储不可变对象。

> Tip
> remember会将对象存储在Composable 中，当调用 remember的Composable被移除后，存储的值也随之消失。

`mutableStateOf`会创建可观察的 `MutableState<T>`，后者是 Compose 运行时可观察类型。

```kotlin
interface MutableState<T> : State<T> {
    override var value: T
}
```

value 有任何更改，系统会安排重组，读取value 的所有Composable 函数。

在Composable中声明 MutableState 对象有三种方法：

+ val mutableState = remember { mutableStateOf(default) }
+ var value by remember { mutableStateOf(default) }
+ val (value, setValue) = remember { mutableStateOf(default) }

这三种方法是等效的，以语法糖的形式提供不同的用法。使用 by 语法需要导入：

```kotlin
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
```

你可以将状态值作为 Composable 的参数，也可以用作逻辑语句中的判断条件。

```kotlin
@Composable
fun HelloContent() {
   Column(modifier = Modifier.padding(16.dp)) {
       var name by remember { mutableStateOf("") }
       if (name.isNotEmpty()) {
           Text(
               text = "Hello, $name!",
               modifier = Modifier.padding(bottom = 8.dp),
               style = MaterialTheme.typography.h5
           )
       }
       OutlinedTextField(
           value = name,
           onValueChange = { name = it },
           label = { Text("Name") }
       )
   }
}
```

> Warning

> 虽然remember可以在重组后保持状态，但如果是应用的配置更新了，比如屏幕旋转，这时候这个状态也会重置。因此，必须使用 rememberSaveable。 rememberSaveable会自动保存可保存的 Bundle 中的值。对于其他值，可以将其传入自定义 Saver 对象。

### 其他可观察对象

Jetpack Compose 不强制使用 MutableState存储状态，也支持其他可观察类型。但在 Jetpack Compose 中读取其他可观察类型之前，必须将其转为 State，以便 Jetpack Compose 可以在状态发生变化时自动重组界面。

其他可用的可观察类型：

+ [LiveData](https://developer.android.google.cn/reference/kotlin/androidx/compose/runtime/livedata/package-summary)
+ [Flow](https://developer.android.google.cn/reference/kotlin/androidx/compose/runtime/package-summary#(kotlinx.coroutines.flow.StateFlow).collectAsState(kotlin.coroutines.CoroutineContext))
+ [RxJava2](https://developer.android.com/reference/kotlin/androidx/compose/runtime/rxjava2/package-summary)

> Tip

> Compose 是通过读取State对象自动重组界面的。 如果在 Compose 中使用 LiveData 等其他可观察类型，应该先将其转换为 State 然后再使用。比如 LiveData<T>.observeAsState()。

> Warning

> 在 Compose 中将可变对象，如 ArrayList或 mutableListOf()等用作状态，可以造成界面无法更新，用户看到的永远是旧的数据。建议使用可观察的数据存储器，如 State>和不可变的 listOf()，而不是使用不可观察的可变对象。

### 状态提升

使用remember存储对象的 Composable 中创建内部状态，使该Composable有了状态，会在其内部保持和修改自己的状态。在调用者不需要控制和管理状态的情况下，这么操作是可以的。但是一般这种Composable不能复用，也不好测试。

因此如果在编写的组件考虑复用的情况下，应该将状态移到 Composable 组件的调用者，保证Composable本身是无状态的，这种操作叫做状态提升。

Jetpack Compose 中一般的状态提升模式是将状态变量替换为两个参数：

+ value:T：要显示的当前值
+ onValueChange:(T) -> Unit：请求更改值的事件，其中 T 是建议的新值

其实，并不一定定义为 onValueChange ，需要根据具体的操作来定义更有意义的名称。比如 onExpand 和 onCollapse。

以这种方式提升的状态具有一些重要的属性：

+ 单一可信来源：通过移动状态而不是复制状态，来确保只有一个可信的数据来源，可以避免一些 bug
+ 封装：只有有状态的Composable能够修改其状态
+ 可共享：可与多个Composable共享提升的状态
+ 可拦截：无状态Composable的调用者可以在更改状态前决定忽略或修改事件
+ 解耦：无状态Composable的状态可以存储在任何位置

在本示例中，您从 HelloContent 中提取 name 和 onValueChange，并按照可组合项的树结构将它们移至可调用 HelloContent 的 HelloScreen 中。

```kotlin
@Composable
fun HelloScreen() {
    var name by rememberSaveable { mutableStateOf("") }

    HelloContent(name = name, onNameChange = { name = it })
}

@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello, $name",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") }
        )
    }
}
```

通过从 HelloContent 中提升出状态，更容易推断该Composable在不同的情况下重复使用它，以及进行测试。HelloContent 与状态的存储方式解耦。解耦意味着，如果您修改或替换 HelloScreen，不必更改 HelloContent 的实现方式。

状态下降、事件上升的这种模式称为“单向数据流”。在这种情况下，状态会从 HelloScreen 下降为 HelloContent，事件会从 HelloContent 上升为 HelloScreen。通过遵循单向数据流，您可以将在界面中显示状态的可组合项与应用中存储和更改状态的部分解耦。

> Note:

> When hoisting state, there are three rules to help you figure out where state should go:

> State should be hoisted to at least the lowest common parent of all composables that use the state (read).

> If two states change in response to the same events they should be hoisted together.

> State should be hoisted to at least the highest level it may be changed (write).

> 提升状态时，有三条规则：

> 1.状态应至少提升到使用该状态(读取)的所有Composable的最低共同父项

> 2.状态应至少提升到它可以发生变化(写入)的最高级别

> 3.如果两种状态发生变化以响应相同的事件，它们应该一直提升。

### 恢复状态

在重新创建 Activity 或进程后，可以使用`rememberSaverable`恢复界面状态。

#### 存储方式

添加到 Bundle 的所有数据类型都会自动保存。如果要保存无法添加到 Bundle 的内容，可以有以下几种方式

#### Parcelize

最简单的解决方案是向对象添加@Parcelize 注解。

```kotlin
@Parcelize
data class City(val name: String, val country: String) : Parcelable

@Composable
fun CityScreen() {
    var selectedCity = rememberSaveable {
        mutableStateOf(City("Madrid", "Spain"))
    }
}
```

#### MapSaver

如果某种原因导致 @Parcelize 不合适，您可以使用 mapSaver 定义自己的规则，规定如何将对象转换为系统可保存到 Bundle 的一组值。

```kotlin
data class City(val name: String, val country: String)

val CitySaver = run {
    val nameKey = "Name"
    val countryKey = "Country"
    mapSaver(
        save = { mapOf(nameKey to it.name, countryKey to it.country) },
        restore = { City(it[nameKey] as String, it[countryKey] as String) }
    )
}

@Composable
fun CityScreen() {
    var selectedCity = rememberSaveable(stateSaver = CitySaver) {
        mutableStateOf(City("Madrid", "Spain"))
    }
}
```

#### ListSaver

为了避免需要为映射定义键，您也可以使用 listSaver 并将其索引用作键：
```kotlin
data class City(val name: String, val country: String)

val CitySaver = listSaver<City, Any>(
    save = { listOf(it.name, it.country) },
    restore = { City(it[0] as String, it[1] as String) }
)

@Composable
fun CityScreen() {
    var selectedCity = rememberSaveable(stateSaver = CitySaver) {
        mutableStateOf(City("Madrid", "Spain"))
    }
}
```

### 管理状态

在前面说到的状态提升，可以简单的把状态进行一定的统一管理。但是如果随着项目功能的丰富，需要跟踪的状态数量也随之增加或者Composable中需要执行业务逻辑时，最好将逻辑和状态事务委派给其他类（状态容器）

下面将介绍如何在 Compose 中以不同方式管理状态。根据Composable的复杂性，需要考虑不同的方案：

+ Composables：用于管理简单的界面元素状态
+ 状态容器：用于管理复杂的界面元素状态且拥有界面逻辑
+ ViewModel：提供对于业务逻辑和 UI 状态的状态容器

状态容器的大小取决于所管理的界面元素的范围，有时候甚至需要将某个状态容器集成到其他状态容器中。

下图所示为 Compose 状态管理所涉及的各实体之间的关系概览。

+ Composable可以信赖于0个或多个状态容器，具体取决于其复杂性
+ 如果需要访问业务逻辑或UI 状态，则可能需要信赖于 ViewModel
+ ViewModel 信赖于业务层或数据层

### 不同类型的状态和逻辑

在 Android 应用中，需要考虑不同类型的状态：

+ UI组件状态是组件的提升状态。例如ScaffoldState用于处理 Scaffold的状态。
+ 界面 UI 状态是界面上需要显示的内容。比如一个商城 APP 上的购物车界面可能包含商品信息、向用户显示的消息或加载标记。该状态通常会和其他层相关联。

此外，逻辑也有不同的类型：

+ 界面操作逻辑和 UI 逻辑：如何在屏幕上显示状态。例如，导航逻辑决定显示哪个界面。
+ 业务逻辑决定如何处理状态变化，通常位于业务层或数据层，而不应该放在 UI 层

### Composables 作为可信来源

如果状态数量较少和逻辑比较简单，在Composable中直接增加逻辑和状态是可以的，与其相关的交互都应该在这个Composable进行。但是如果将它传递给其他Composable，这就不符合单一可信来源原则，而且会使调试更多困难。

```kotlin
@Composable
fun MyApp() {
    MyTheme {
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()

        Scaffold(scaffoldState = scaffoldState) {
            MyContent(
                showSnackbar = { message ->
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(message)
                    }
                }
            )
        }
    }
}
```

### 状态容器作为可信来源

当Composable涉及多个界面的状态等复杂逻辑时，应将相应事务委派给状态容器。这样更易于单独对该逻辑进行测试，还降低了Composable的复杂性。保证Composable只是负责展示，而状态容器负责逻辑和状态

在上面 MyApp 的例子中，如果增加更多的逻辑，那么就可以创建一个MyAppState状态容器来管理

```kotlin
// Plain class that manages App's UI logic and UI elements' state
class MyAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val resources: Resources,
    /* ... */
) {
    val bottomBarTabs = /* State */

    // Logic to decide when to show the bottom bar
    val shouldShowBottomBar: Boolean
        get() = /* ... */

    // Navigation logic, which is a type of UI logic
    fun navigateToBottomBarRoute(route: String) { /* ... */ }

    // Show snackbar using Resources
    fun showSnackbar(message: String) { /* ... */ }
}

@Composable
fun rememberMyAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    resources: Resources = LocalContext.current.resources,
    /* ... */
) = remember(scaffoldState, navController, resources, /* ... */) {
    MyAppState(scaffoldState, navController, resources, /* ... */)
}
```
因为在使用MyAppState 的时候需要使用remember来进行信赖，所以通常情况下可以创建一个rememberMyAppState方法来直接返回MyAppState实例。

那么现在 MyApp 的代码就可以变得很简单了
```kotlin
@Composable
fun MyApp() {
    MyTheme {
        val myAppState = rememberMyAppState()
        Scaffold(
            scaffoldState = myAppState.scaffoldState,
            bottomBar = {
                if (myAppState.shouldShowBottomBar) {
                    BottomBar(
                        tabs = myAppState.bottomBarTabs,
                        navigateToRoute = {
                            myAppState.navigateToBottomBarRoute(it)
                        }
                    )
                }
            }
        ) {
            NavHost(navController = myAppState.navController, "initial") { /* ... */ }
        }
    }
}
```

### ViewModel 作为可信来源

ViewModel 是一种特殊的状态容器，主要负责：

+ 对应用的业务逻辑或数据进行处理，这个逻辑通常是在业务层或数据层
+ 处理即将在界面上展示的应用数据

ViewModel 的生命周期往往是比较长的，原因是它们在配置发生变化后仍然有效。ViewModel 可以遵循 Activity、Fragment、或导航（如果使用了导航库）的生命周期。正因为 ViewModel 的生命周期较长，因此不应该长期持有和 Composable 密切相关的一些状态，否则，可以会导致内存泄漏。

```kotlin
data class ExampleUiState(
    dataToDisplayOnScreen: List<Example> = emptyList(),
    userMessages: List<Message> = emptyList(),
    loading: Boolean = false
)

class ExampleViewModel(
    private val repository: MyRepository,
    private val savedState: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf<ExampleUiState>(...)
        private set

    // Business logic
    fun somethingRelatedToBusinessLogic() { ... }
}

@Composable
fun ExampleScreen(viewModel: ExampleViewModel = viewModel()) {

    val uiState = viewModel.uiState
    ...

    Button(onClick = { viewModel.somethingRelatedToBusinessLogic() }) {
        Text("Do something")
    }
}
```

> Tip
> 如果 ViewModel 中包含要在进程重建后保留的状态，请使用`SavedStateHandle`。

[官方文档](https://developer.android.google.cn/jetpack/compose/state)



## 生命周期

### Side-Effects

在学习生命周期之前先了解一下 Side-Effects。翻译过来就是副作用，那么在程序中什么是副作用呢？

一个函数有以下情况(不仅限)说明有副作用：

- 引用和修改了函数之外的变量
- 改变了函数的入参
- 调用了其他有副作用的函数

Composable 本身是没有副作用的。Jetpack Compose 提供了不同的 API 来让 Composable 能感知外部数据的改变。

由于使用这些API带来各种可能性，容易过度使用。所以确保在其中完成的工作与界面相关，并且不会打乱单一数据源原则。

### LanuchedEffect

如果需要在 Compasable 内安全调用挂起函数，可以使用 `LaunchedEffect` 。 `LaunchedEffect` 会自动启动一个协程，并将代码块作为参数传递。当 `LaunchedEffect` 离开 Composable 或 Composable 销毁时，协程也将取消。如果 `LaunchedEffect` 的 key 值改变了，系统将取消现有协程，并在新的协程中启动新的挂起函数。

例如，在 `Scaffold` 中显示 `Snackbar` 是通过 `SnackbarHostState.showSnackbar` 来完成的。这个函数是一个挂起函数。

```kotlin
@Composable
fun MyScreen(
    state: UiState<List<Movie>>,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    // If the UI state contains an error, show snackbar
    if (state.hasError) {

        // `LaunchedEffect` will cancel and re-launch if
        // `scaffoldState.snackbarHostState` changes
        LaunchedEffect(scaffoldState.snackbarHostState) {
            // Show snackbar using a coroutine, when the coroutine is cancelled the
            // snackbar will automatically dismiss. This coroutine will cancel whenever
            // `state.hasError` is false, and only start when `state.hasError` is true
            // (due to the above if-check), or if `scaffoldState.snackbarHostState` changes.
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Error message",
                actionLabel = "Retry message"
            )
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        /* ... */
    }
}
```

在上面的代码中，如果state 中有错误，就会触发协程，如果没有错误，则将取消协程。

### rememberCoroutineScope

由于 `LanchedEffect` 是Composable 函数，只能在其他 Composable 中使用。为了可以在 Composable 之外启动协程，且在离开 Composable 时自动取消协程，可以使用 `rememberCoroutineScope` 。此外，如果需要手动控制协程的生命周期时，也可以使用 `rememberCoroutineScope` 。

`rememberCoroutineScope` 是一个 Composable 函数，会返回一个  `CoroutineScope` ，这个  `CoroutineScope` 会绑定到调用它的 Composable 。

根据上面的例子，当用户点击按钮时，可以使用以下代码来显示 Snackbar

```kotlin
@Composable
fun MoviesScreen(scaffoldState: ScaffoldState = rememberScaffoldState()) {

    // Creates a CoroutineScope bound to the MoviesScreen's lifecycle
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Column {
            /* ... */
            Button(
                onClick = {
                    // Create a new coroutine in the event handler to show a snackbar
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Something happened!")
                    }
                }
            ) {
                Text("Press me")
            }
        }
    }
}
```

### rememberUpdatedState

上面说到 `LaunchedEffect` 可以传入一个 key 值，当 key 改变时 `LaunchedEffect` 会重启。但是在某些情况下，不希望捕获某个值，该值发生变化时，不想让 `LaunchedEffect` 重启。因此需要使用 `rememberUpdatedState` 来创建对可捕获和更新的该值的引用。

假设有一个LandingScreen，需要在一段时间后消失。即使 LandingScreen 在这一段时间内进行了重组，也不应该重新计时

```kotlin
@Composable
fun LandingScreen(onTimeout: () -> Unit) {

    // This will always refer to the latest onTimeout function that
    // LandingScreen was recomposed with
    val currentOnTimeout by rememberUpdatedState(onTimeout)

    // Create an effect that matches the lifecycle of LandingScreen.
    // If LandingScreen recomposes, the delay shouldn't start again.
    LaunchedEffect(true) {
        delay(SplashWaitTimeMillis)
        currentOnTimeout()
    }

    /* Landing screen content */
}
```

为了创建与 Composable 生命周期相匹配的 `LaunchedEffect`，可以将一个不会发生改变的常量（如`Unit`或`true`）作为参数传递。

### DisposableEffect

对于需要对于某个值改变时或 Composable 退出后进行销毁或清理操作时，可以使用 `DisposableEffect`。当 `DisposableEffect` 的 key 发生改变时，会调用 `onDispose` 方法，可以在方法中作清理操作，然后再次调用重启。

例如，在使用 `LifecycleObserver` 进行 `Lifecycle` 事件进行监听时，可以根据需要使用 `DisposableEffect` 来注册和取消观察器。

```kotlin
@Composable
fun HomeScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit, // Send the 'started' analytics event
    onStop: () -> Unit // Send the 'stopped' analytics event
) {
    // Safely update the current lambdas when a new one is provided
    val currentOnStart by rememberUpdatedState(onStart)
    val currentOnStop by rememberUpdatedState(onStop)

    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                currentOnStart()
            } else if (event == Lifecycle.Event.ON_STOP) {
                currentOnStop()
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    /* Home screen content */
}
```

在上面的代码中，`DisposableEffect` 将 `observer` 添加到 `lifecycleOwner` 。如果 `lifecycleOwner` 发生变化，会先`removeObserver`，然后再重启。

`DisposableEffect` 必须在其代码块中添加 `onDispose` 子句作为结束语句。否则，IDE 将会报错。

### SideEffect

需要与非 Compose 管理的对象共享 Compose 状态时，可以使用 `SideEffect`，`SideEffect` 在每次重组完成后都会被调用。

例如，当需要进行统计用户类型数据时：

```kotlin
@Composable
fun rememberAnalytics(user: User): FirebaseAnalytics {
    val analytics: FirebaseAnalytics = remember {
        /* ... */
    }

    // On every successful composition, update FirebaseAnalytics with
    // the userType from the current User, ensuring that future analytics
    // events have this metadata attached
    SideEffect {
        analytics.setUserProperty("userType", user.userType)
    }
    return analytics
}
```

### produceState

`produceState` 会启动一个协程，将非 Compose 状态转换为 Compose 状态。

以下示例展示了如何使用 `produceState` 从网络加载图像。`loadNetworkImage` 函数会返回可以在其他Composable中使用的 `State`。

```kotlin
@Composable
fun loadNetworkImage(
    url: String,
    imageRepository: ImageRepository
): State<Result<Image>> {

    // Creates a State<T> with Result.Loading as initial value
    // If either `url` or `imageRepository` changes, the running producer
    // will cancel and will be re-launched with the new inputs.
    return produceState<Result<Image>>(initialValue = Result.Loading, url, imageRepository) {

        // In a coroutine, can make suspend calls
        val image = imageRepository.load(url)

        // Update State with either an Error or Success result.
        // This will trigger a recomposition where this State is read
        value = if (image == null) {
            Result.Error
        } else {
            Result.Success(image)
        }
    }
}
```

### derivedStateOf

如果某个状态是从其他状态对象计算或派生得出的，请使用 derivedStateOf。使用此函数可确保仅当计算中使用的状态之一发生变化时才会进行计算。

以下示例展示了基本的“待办事项”列表，其中具有用户定义的高优先级关键字的任务将首先显示：

```kotlin
@Composable
fun TodoList(highPriorityKeywords: List<String> = listOf("Review", "Unblock", "Compose")) {

    val todoTasks = remember { mutableStateListOf<String>() }

    // Calculate high priority tasks only when the todoTasks or highPriorityKeywords
    // change, not on every recomposition
    val highPriorityTasks by remember(highPriorityKeywords) {
        derivedStateOf { todoTasks.filter { it.containsWord(highPriorityKeywords) } }
    }

    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            items(highPriorityTasks) { /* ... */ }
            items(todoTasks) { /* ... */ }
        }
        /* Rest of the UI where users can add elements to the list */
    }
}
```

在以上代码中，derivedStateOf 保证每当 todoTasks 发生变化时，系统都会执行 highPriorityTasks  计算，并相应地更新界面。如果 highPriorityKeywords 发生变化，系统将执行 remember  代码块，并且会创建新的派生状态对象并记住该对象，以代替旧的对象。由于执行过滤以计算 highPriorityTasks  的成本很高，因此应仅在任何列表发生更改时才执行，而不是在每次重组时都执行。

此外，更新 derivedStateOf 生成的状态不会导致可组合项在声明它的位置重组，Compose 仅会对返回状态为已读的可组合项（在本例中，指 LazyColumn 中的可组合项）进行重组。

该代码还假设 highPriorityKeywords 的变化频率显著低于 todoTasks。否则，该代码会使用 remember(todoTasks, highPriorityKeywords) 而不是 derivedStateOf。

### snapshotFlow

使用 snapshotFlow 将 State 对象转换为冷 Flow。snapshotFlow  会在收集到块时运行该块，并发出从块中读取的 State 对象的结果。当在 snapshotFlow 块中读取的 State  对象之一发生变化时，如果新值与之前发出的值不相等，Flow 会向其收集器发出新值（此行为类似于  Flow.distinctUntilChanged 的行为）。

下列示例显示了一项附带效应，是系统在用户滚动经过要分析的列表的首个项目时记录下来的：

```kotlin
val listState = rememberLazyListState()

LazyColumn(state = listState) {
    // ...
}

LaunchedEffect(listState) {
    snapshotFlow { listState.firstVisibleItemIndex }
        .map { index -> index > 0 }
        .distinctUntilChanged()
        .filter { it == true }
        .collect {
            MyAnalyticsService.sendScrolledPastFirstItemEvent()
        }
}
```

在上方代码中，listState.firstVisibleItemIndex 被转换为一个 Flow，从而可以受益于 Flow 运算符的强大功能。

## CompositionLocal

### CompositionLocal 简介

通常在 Compose 中，数据以参数形式显式向下传递到每个 Composable 中。这样对于一些使用频率很高的数据(如颜色或类型样式)来说就很麻烦了，需要一层一层传递。

```kotlin
@Composable
fun MyApp() {
    // Theme information tends to be defined near the root of the application
    val colors = …
}

// Some composable deep in the hierarchy
@Composable
fun SomeTextLabel(labelText: String) {
    Text(
        text = labelText,
        color = // ← need to access colors here
    )
}
```

为了支持无需显式传递参数给其他 Composable，Compose 提供了 CompositionLocal ，可以让你创建以树为作用域的具名对象，用作数据隐匿流向子界面的方式。

MaterialTheme 对象提供了三个 CompositionLocal 实例，即 colors、typography 和 shapes。可以在任何地方拿到这些实例进行使用。具体来说，这些 MaterialTheme的 colors、shapes 和 typography 属性就是访问 LocalColors、LocalShapes 和 LocalTypography。

```kotlin
@Composable
fun MyApp() {
    // Provides a Theme whose values are propagated down its `content`
    MaterialTheme {
        // New values for colors, typography, and shapes are available
        // in MaterialTheme's content lambda.

        // ... content here ...
    }
}

// Some composable deep in the hierarchy of MaterialTheme
@Composable
fun SomeTextLabel(labelText: String) {
    Text(
        text = labelText,
        // `primary` is obtained from MaterialTheme's
        // LocalColors CompositionLocal
        color = MaterialTheme.colors.primary
    )
}
```

CompositionLocal 实例的作用域限定为Composable的一部分，因此可以在结构树的不同级别提供不同的值。CompositionLocal 的 current 值对应于Composable的某个父级提供的就近值。

如需为 CompositionLocal 提供新值，请使用 CompositionLocalProvider 及其 provides infix 函数，该函数将 CompositionLocal 键与 value 相关联。在访问 CompositionLocal 的 current 属性时，CompositionLocalProvider 的 content lambda 将获取提供的值。提供新值后，Compose 会重组读取 CompositionLocal 的组合部分。

例如，LocalContentAlpha 包含用于文本和图标的首选内容 Alpha 值，以强调或弱化界面的不同部分。在以下示例中，CompositionLocalProvider 用于为组合的不同部分提供不同的值。

```kotlin
@Composable
fun CompositionLocalExample() {
    MaterialTheme { // MaterialTheme sets ContentAlpha.high as default
        Column {
            Text("Uses MaterialTheme's provided alpha")
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("Medium value provided for LocalContentAlpha")
                Text("This Text also uses the medium value")
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                    DescendantExample()
                }
            }
        }
    }
}

@Composable
fun DescendantExample() {
    // CompositionLocalProviders also work across composable functions
    Text("This Text uses the disabled alpha now")
}
```

在上面的所有示例中，由 Material 可组合项在内部使用 CompositionLocal 实例。如需访问 CompositionLocal 的当前值，请使用其 current 属性。在以下示例中，使用 Android 应用中常用的 LocalContext CompositionLocal 的当前 Context 值来设置文本格式：

```kotlin
@Composable
fun FruitText(fruitSize: Int) {
    // Get `resources` from the current value of LocalContext
    val resources = LocalContext.current.resources
    val fruitText = remember(resources, fruitSize) {
        resources.getQuantityString(R.plurals.fruit_title, fruitSize)
    }
    Text(text = fruitText)
}
```

> Note

> 注意：CompositionLocal 对象或常量通常带有 Local 前缀，以便在 IDE 中利用自动填充功能提高可检测性。

### 创建自己的 CompositionLocal

CompositionLocal 是隐式向下传递数据的工具。

使用 CompositionLocal 的一个关键信号是该参数为横切参数且中间层的实现不应知道该参数的存在，因为让这些中间层知道可能会限制 Composable 的功用。例如，对 Android 权限的查询是由 CompositionLocal在后台提供的。媒体选择器可以去访问设备上受权限保护的内容而无需修改 API。

但不建议考完试使用 CompositionLocal ，因为它存在一些缺点：

+ CompositionLocal 使得 Composable 的行为更难推断。
+ 可能没有明确的可信来源，因为它可能在任何地方就改变了。因此增加调试的难度，必须向上查找给 cureent 提供值的地方。

#### 决定是否使用 CompositionLocal

+ CompositionLocal 应具有一个默认值
+ 非以树或子层次结构为作用域

#### 创建 CompositionLocal

有两个 API 可以创建 CompositionLocal： - compositionLocalOf：在重组的过程中改变对应值，只会让调用该值的地方无效 - staticCompositionLocalOf:和compositionLocalOf不同，改变对应值会让整个 content lambda 重组

当值几乎不变的情况下，建议使用staticCompositionLocalOf，可以提高性能。

例如，想让 APP 根据系统主题来使用不同的抬高阴影时，由于在整个界面树中进行使用，所以可以使用CompositionLocal。
LocalElevations.kt

```kotlin
data class Elevations(val card: Dp = 0.dp, val default: Dp = 0.dp)

//定义一个全局的CompositionLocal并初始化
val LocalElevations = compositionLocalOf { Elevations() }
```

#### 为CompositionLocal赋值

MyActivity.kt

```kotlin
class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // 根据主题生成不同的Elevations
            val elevations = if (isSystemInDarkTheme()) {
                Elevations(card = 1.dp, default = 1.dp)
            } else {
                Elevations(card = 0.dp, default = 0.dp)
            }

            //绑定一个elevation到LocalElevations上
            CompositionLocalProvider(LocalElevations provides elevations) {
                // ... Content goes here ...
                // 在子级页面就可以调用LocalElevations.current来取到当前的Elevations
            }
        }
    }
}
```

#### 使用CompositionLocal

CompositionLocal.current 根据就近原则返回CompositionLocalProvider提供的值

```kotlin
@Composable
fun SomeComposable() {
    // Access the globally defined LocalElevations variable to get the
    // current Elevations in this part of the Composition
    Card(elevation = LocalElevations.current.card) {
        // Content
    }
}
```

### 需考虑的替代方案

某些场景下，CompositionLocal可能不合适，甚至过度使用。

#### 显式参数

在极简单逻辑情况，应尽量使用显示参数传递，且只传递有效参数，避免造成参数过多。

#### 控制反转

另一种避免参数过多或无效参数的方法就是控制反转。一些逻辑可以不在子级页面进行，而应该转移到父级页面来进行。

例如下面的例子中，在子级页面使用了 viewModel 调用 loadData

```kotlin
@Composable
fun MyComposable(myViewModel: MyViewModel = viewModel()) {
    // ...
    MyDescendant(myViewModel)
}

@Composable
fun MyDescendant(myViewModel: MyViewModel) {
    Button(onClick = { myViewModel.loadData() }) {
        Text("Load data")
    }
}
```

MyDescendant 可能需要承担很多逻辑，将 MyViewModel 作为参数传递可能会降低 MyDescendant 的可重用性，因此可以考虑控制反转来优化这个代码

```kotlin
@Composable
fun MyComposable(myViewModel: MyViewModel = viewModel()) {
    // ...
    ReusableLoadDataButton(
        onLoadClick = {
            myViewModel.loadData()
        }
    )
}

@Composable
fun ReusableLoadDataButton(onLoadClick: () -> Unit) {
    Button(onClick = onLoadClick) {
        Text("Load data")
    }
}
```
在某些场景下控制反转可以将子级脱离出来，达到高度复用，可以更灵活。

同样，可以用 lambda 表达式来实现
```kotlin
@Composable
fun MyComposable(myViewModel: MyViewModel = viewModel()) {
    // ...
    ReusablePartOfTheScreen(
        content = {
            Button(
                onClick = {
                    myViewModel.loadData()
                }
            ) {
                Text("Confirm")
            }
        }
    )
}

@Composable
fun ReusablePartOfTheScreen(content: @Composable () -> Unit) {
    Column {
        // ...
        content()
    }
}
```

## 动画

[参考官方文档](https://developer.android.google.cn/jetpack/compose/animation)



## **Accompanist**  

### 概述

**Accompanist ：Jetpack Compose 的工具包**

### 官方地址

<https://google.github.io/accompanist/>

<https://github.com/google/accompanist>

[![Maven Central](https://img.shields.io/maven-central/v/com.google.accompanist/accompanist-insets)](https://search.maven.org/search?q=g:com.google.accompanist)

### 功能

#### Insets

设置 [WindowsInsets](https://developer.android.google.cn/reference/kotlin/android/view/WindowInsets)

配置

```
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.google.accompanist:accompanist-insets:<version>"
    // If using insets-ui
    implementation "com.google.accompanist:accompanist-insets-ui:<version>"
}
```

#### System UI Controller

可以设置系统状态栏的颜色和显示隐藏

配置

```
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.google.accompanist:accompanist-systemuicontroller:<version>"
}
```

#### AppCompat Theme Adapter

引用 AppCompat 的 xml 形式主题

配置

```
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.google.accompanist:accompanist-appcompat-theme:<version>"
}
```

#### Pager

类似 Android 里面的 ViewPager

配置

```
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.google.accompanist:accompanist-pager:<version>"

    // If using indicators, also depend on 
    implementation "com.google.accompanist:accompanist-pager-indicators:<version>"
}
```

#### Permissions

权限请求

配置

```
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.google.accompanist:accompanist-permissions:<version>"
}
```

用法参考[PermissionSample]([ComposeBasic/PermissionSample.kt at master · liaojie1314/ComposeBasic (github.com)](https://github.com/liaojie1314/ComposeBasic/blob/master/app/src/main/java/com/example/composebasic/components/PermissionSample.kt) )

#### Placeholder

更简单的使用数据未加载时的占位符

配置

```
repositories {
    mavenCentral()
}

dependencies {
    // If you're using Material, use accompanist-placeholder-material
    implementation "com.google.accompanist:accompanist-placeholder-material:<version>"

    // Otherwise use the foundation version
    implementation "com.google.accompanist:accompanist-placeholder:<version>"
}
```

#### Flow Layouts

Flexbox-line 布局

配置

```
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.google.accompanist:accompanist-flowlayout:<version>"
}
```

#### Navigation-Animation

导航动画

配置

```
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.google.accompanist:accompanist-navigation-animation:<version>"
}
```

#### Navigation-Material

提供[Compose Material](https://developer.android.com/jetpack/androidx/releases/compose-material) 支持，比如 model bottom sheets

配置

```
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.google.accompanist:accompanist-navigation-material:<version>"
}
```

#### Drawable Painter

更灵活的使用 Android Drabables

配置

```
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.google.accompanist:accompanist-drawablepainter:<version>"
}
```

#### Swipe to Refresh

下拉刷新，类似 Android 的 SwipeRefreshLayout

配置

```
repositories {
    mavenCentral()
}

dependencies {
    implementation "com.google.accompanist:accompanist-swiperefresh:<version>"
}
```

#### WebView

配置

```
repositories { 
	mavenCentral() 
}

dependencies { 
	implementation “com.google.accompanist:accompanist-webview:” 
}
```



