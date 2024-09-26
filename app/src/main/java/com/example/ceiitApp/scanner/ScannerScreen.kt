package com.example.ceiitApp.scanner

import android.Manifest
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScannerScreen() {
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Escanear Código QR",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (cameraPermissionState.hasPermission) {
            CameraPreview()
        } else {
            Button(onClick = {
                coroutineScope.launch {
                    cameraPermissionState.launchPermissionRequest()
                }
            }) {
                Text(text = "Permitir acceso a la cámara")
            }
        }
    }
}

@Composable
fun CameraPreview() {
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    AndroidView(
        factory = { ctx ->
            val cameraProvider = cameraProviderFuture.get()
            val preview = androidx.camera.core.Preview.Builder().build() // Aquí corregimos el uso de Preview.Builder

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            val previewView = androidx.camera.view.PreviewView(ctx).apply {
                scaleType = androidx.camera.view.PreviewView.ScaleType.FILL_CENTER
            }

            preview.setSurfaceProvider(previewView.surfaceProvider)
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    ctx as androidx.lifecycle.LifecycleOwner,
                    cameraSelector,
                    preview
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }

            previewView
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun PreviewScannerScreen() {
    ScannerScreen()
}
