import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

import com.example.ceiitApp.models.*
import com.example.ceiitApp.themes.MainTheme

@Composable
fun StandardDetailScreen(
    obj: Any,
    objectType: String,
    onBackClick: () -> Unit,
    onStartLoanClick: () -> Unit
) {
    val title: String
    val description: String
    val imageUrl: String

    when (objectType) {
        "prestamo" -> {
            val prestamo = obj as Prestamo
            title = prestamo.nombre
            description = """
                Categoría: ${prestamo.categoria}
                Estado: ${prestamo.estado}
                Prestado el: ${prestamo.fechaPrestamo}
                Devolución: ${prestamo.fechaDevolucion}
            """.trimIndent()
            imageUrl = prestamo.urlImagen
        }
        "objeto" -> {
            val objeto = obj as Objeto
            title = objeto.nombre
            description = """
                Descripción: ${objeto.descripcion}
                Estado: ${objeto.estado}
                Ubicación: ${objeto.ubicacion}
                Categoría: ${objeto.categoria}
            """.trimIndent()
            imageUrl = objeto.urlImagen
        }
        "noticia" -> {
            val noticia = obj as Noticia
            title = noticia.titulo
            description = noticia.descripcion
            imageUrl = noticia.urlImagen
        }
        else -> {
            title = "Unknown"
            description = "Unknown object type"
            imageUrl = ""
        }
    }

    Scaffold(
        topBar = {
            MainTheme {
                TopAppBar(
                    title = { Text(text = title) },
                    navigationIcon = {
                        IconButton(onClick = { onBackClick() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Volver"
                            )
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                elevation = 4.dp
            ) {
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 8.dp))

            if (objectType == "objeto") {
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onStartLoanClick() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Iniciar Préstamo")
                }
            }

        }
    }
}
