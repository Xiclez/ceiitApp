package com.example.ceiitApp.scanner

import android.util.Size
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.ceiitApp.models.Objeto
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.delay


@Composable
fun ScannerScreen(navController: NavHostController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var scannedQr by remember { mutableStateOf<String?>(null) }
    var scannedObject by remember { mutableStateOf<Objeto?>(null) }
    var showCard by remember { mutableStateOf(false) }

    val qrObjects = listOf(
        Objeto(
            id = "1",
            nombre = "SIERRA DE MESA RYOBI",
            descripcion = "Sierra de mesa para trabajos de carpintería.",
            estado = "Disponible",
            categoria = "Herramientas",
            urlImagen = "https://cdn.homedepot.com.mx/productos/133485/133485-d.jpg",
            ubicacion = "Taller de Madera y Metal",
            qr = "qr_sierra_mesa"
        ),
        Objeto(
            id = "2",
            nombre = "IMPRESORA 3D CREALITY",
            descripcion = "Impresora 3D para prototipado rápido.",
            estado = "En Uso",
            categoria = "Impresoras",
            urlImagen = "https://www.3dmarket.mx/wp-content/uploads/2022/07/Impresora-3D-Ender-3-S1-Creality.webp",
            ubicacion = "FabLab",
            qr = "qr_impresora_3d"
        ),
        Objeto(
            id = "3",
            nombre = "LÁSER DE CORTE",
            descripcion = "Máquina láser para corte de precisión.",
            estado = "En Mantenimiento",
            categoria = "Máquinas",
            urlImagen = "https://m.media-amazon.com/images/I/71zrbVEpAaL.jpg",
            ubicacion = "FabLab",
            qr = "qr_laser_corte"
        )
    )

    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val previewView = remember { androidx.camera.view.PreviewView(context) }

    // QR code scanner setup
    LaunchedEffect(scannedQr) {
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .setTargetResolution(Size(1280, 720))
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            val imageAnalysis = androidx.camera.core.ImageAnalysis.Builder()
                .setTargetResolution(Size(1280, 720))
                .setBackpressureStrategy(androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            val scanner = BarcodeScanning.getClient()
            imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(context)) { imageProxy ->
                processImageProxy(scanner, imageProxy) { result: Barcode? ->
                    scannedQr = result?.rawValue
                    scannedObject = qrObjects.find { it.qr == scannedQr }
                    showCard = scannedObject != null
                }
            }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageAnalysis
                )
            } catch (exc: Exception) {
                // Handle any errors (including if the camera cannot be initialized)
            }
        }, ContextCompat.getMainExecutor(context))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Escanear QR") }
            )
        }
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AndroidView(
                factory = { previewView },
                modifier = Modifier.fillMaxSize()
            )

            Text(
                text = "Escaneando QR...",
                fontSize = 24.sp,
                color = Color.Gray
            )

            // Animate the card when a QR object is found
            if (scannedObject != null) {
                AnimatedVisibility(
                    visible = showCard,
                    enter = slideInVertically(initialOffsetY = { fullHeight -> fullHeight }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { fullHeight -> fullHeight }) + fadeOut()
                ) {
                    ObjectDetailCard(
                        objeto = scannedObject!!,
                        onStartLoanClick = {
                            navController.navigate("confirmacionPrestamo/${scannedObject!!.id}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ObjectDetailCard(objeto: Objeto, onStartLoanClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(400.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(objeto.urlImagen),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = objeto.nombre,
                style = MaterialTheme.typography.h6,
                color = Color.Black,
                fontSize = 20.sp
            )
            Text(
                text = objeto.descripcion,
                style = MaterialTheme.typography.body1,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onStartLoanClick) {
                Text("Iniciar Préstamo")
            }
        }
    }
}

@OptIn(androidx.camera.core.ExperimentalGetImage::class)
private fun processImageProxy(
    scanner: com.google.mlkit.vision.barcode.BarcodeScanner,
    imageProxy: androidx.camera.core.ImageProxy,
    onQrCodeScanned: (Barcode?) -> Unit
) {
    val mediaImage = imageProxy.image ?: return
    val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
    scanner.process(image)
        .addOnSuccessListener { barcodes ->
            onQrCodeScanned(barcodes.firstOrNull { it.rawValue != null })
        }
        .addOnFailureListener {
            onQrCodeScanned(null)
        }
        .addOnCompleteListener {
            imageProxy.close()
        }
}
