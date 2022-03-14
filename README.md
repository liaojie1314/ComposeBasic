[资料来源:https://docs.bughub.icu/compose](https://docs.bughub.icu/compose)

# Jetpack Compose 是什么？

Jetpack Compose 是用于构建原生 Android 界面的新工具包。它可简化并加快 Android 上的界面开发，使用更少的代码、强大的工具和直观的 Kotlin API，快速让应用生动而精彩。

# 环境

Android Studio一定要确定版本是Arctic Fox | 2020.3.1之后的版本（包含）

# 简单组件

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

###用法:

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
    + TextAlign.End
    + TextAlign.Justify

+ lineHeight 设置文本行高

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

