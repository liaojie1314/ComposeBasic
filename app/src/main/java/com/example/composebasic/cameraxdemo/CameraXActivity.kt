package com.example.composebasic.cameraxdemo

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.composebasic.R
import com.example.composebasic.cameraxdemo.ui.theme.ComposeBasicTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

class CameraXActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val permissionState =
                        rememberPermissionState(permission = Manifest.permission.CAMERA)
                    when (permissionState.status) {
                        // If the camera permission is granted, then show screen with the feature enabled
                        PermissionStatus.Granted -> {
                            CameraX()
                        }
                        is PermissionStatus.Denied -> {
                            Column(modifier = Modifier.fillMaxSize()) {
                                Row(modifier = Modifier.weight(1f)) {

                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(onClick = {
                                        permissionState.launchPermissionRequest()
                                    }) {
                                        Image(
                                            painter = painterResource(id = R.mipmap.lock),
                                            contentDescription = ""
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CameraX() {
    val lifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current

    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }

    val previewView = remember {
        PreviewView(context).apply {
            id = R.id.preview_view
        }
    }

    val imageCapture = remember {
        ImageCapture.Builder().build()
    }

    val imageUri = remember {
        mutableStateOf<Uri?>(null)
    }

    val fileUtils: FileUtils by lazy {
        FileUtilsImpl()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize()) {
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = androidx.camera.core.Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )
                } catch (e: Exception) {
                    Log.e("Exception", e.toString())
                }
            }, ContextCompat.getMainExecutor(context))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            imageUri.value?.let {
                Image(painter = rememberImagePainter(data = it, builder = {
                    transformations(CircleCropTransformation())
                }), contentDescription = "", modifier = Modifier.size(60.dp))
            }
            Spacer(modifier = Modifier.width(24.dp))
            IconButton(onClick = {
                fileUtils.createDirectoryIfNotExist(context)
                val file = fileUtils.createFile(context)
                val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()
                imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(context),
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                            val saveUri = Uri.fromFile(file)
                            Toast.makeText(context, saveUri.path, Toast.LENGTH_SHORT).show()
                        }

                        override fun onError(exception: ImageCaptureException) {
                            Log.e("--onError--", exception.toString())
                        }
                    })
            }) {
                Image(painter = painterResource(id = R.mipmap.camera), contentDescription = "")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CameraXPreview() {
    ComposeBasicTheme {
        CameraX()
    }
}