package com.example.ceiitApp.home

import StandardHomeCard
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import android.net.Uri
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
//import com.example.ceiitApp.R
import com.example.ceiitApp.themes.MainTheme
import com.example.ceiitApp.models.*
import com.google.gson.Gson

// Dummy data for preview purposes
val prestamos = listOf(
    Prestamo(
        "1",
        "SIERRA DE MESA RYOBI",
        "Herramientas",
        "2023-09-01",
        "2023-09-10",
        "Activo",
        "https://cdn.homedepot.com.mx/productos/133485/133485-d.jpg"
    ),
    Prestamo(
        "2",
        "IMPRESORA 3D CREALITY",
        "Impresoras",
        "2023-08-15",
        "2023-08-20",
        "Finalizado",
        "https://www.3dmarket.mx/wp-content/uploads/2022/07/Impresora-3D-Ender-3-S1-Creality.webp"
    ),
    Prestamo(
        "3",
        "SUBLIMADORA CACHUCHAS",
        "Sublimación",
        "2023-07-10",
        "2023-07-20",
        "Cancelado",
        "https://resources.sears.com.mx/medios-plazavip/mkt/6109c9f131422_gorrajpg.jpg"
    )
)
val objetos = listOf(
    Objeto("1", "Sierra de Mesa","Test Desc", "Disponible", "Herramientas","https://cdn.homedepot.com.mx/productos/133485/133485-d.jpg","Test Loc","testqr"),
    Objeto("2", "Impresora 3D", "Test Desc","En Uso", "Impresoras","https://www.3dmarket.mx/wp-content/uploads/2022/07/Impresora-3D-Ender-3-S1-Creality.webp","Test Loc","testqr"),
    Objeto("3", "Láser de Corte","Test Desc", "En Mantenimiento","Máquinas", "https://m.media-amazon.com/images/I/71zrbVEpAaL.jpg","Test Loc","testqr"),
    Objeto("4", "Sublimadora de Gorras","Test Desc", "Disponible","Sublimación", "https://resources.sears.com.mx/medios-plazavip/mkt/6109c9f131422_gorrajpg.jpg","Test Loc","testqr"),
    Objeto("5", "Taladro Inalámbrico","Test Desc", "Disponible","Herramientas", "https://static.grainger.com/rp/s/is/image/Grainger/22UT50_AS02?glgmain$","Test Loc","testqr")
)

val noticias = listOf(
    Noticia("1","Feria de Prototipos", "Asiste a la próxima feria de prototipos en el FabLab.","https://scontent.fcuu3-1.fna.fbcdn.net/v/t1.6435-9/41009288_726101027725285_4842723373114982400_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=25d718&_nc_ohc=cZFDV0ggc5YQ7kNvgHu4BnO&_nc_ht=scontent.fcuu3-1.fna&_nc_gid=A4fqEzDM0-30t9LGgGqkUkM&oh=00_AYBH5PFSv6FC7MQhu3EBSZJBxdMMJG0j9YU73FCuqNYu_Q&oe=671D4380"),
    Noticia("2","Mantenimiento de Equipos", "Los equipos de impresión 3D estarán en mantenimiento la próxima semana.","https://blog.analitek.com/hubfs/mantenimiento-preventivo-y-correctivo-de-la-pc-800x531_thumb-compressor.jpg"),
    Noticia("3","Nuevos objetos para préstamo", "Se han adquirido nuevos equipos de corte láser y sublimación.","https://media.istockphoto.com/id/1366258243/es/vector/ilustraci%C3%B3n-vectorial-etiqueta-de-nueva-llegada-banner-web-moderno-sobre-fondo-amarillo.jpg?s=1024x1024&w=is&k=20&c=cMYItBTtiysoOzF2l-0_mSgk4vakn0KLHwr1PEUC0UE="),
    Noticia("4","Taller de robótica", "Participa en el próximo taller de robótica avanzada.","https://imancorpfoundation.org/wp-content/uploads/2024/02/Captura-3.jpg"),
    Noticia("5","Actualización de software", "Se ha implementado un nuevo software de gestión de préstamos.","https://www.faronics.com/assets/MC-October-1-01.png")
)
@Composable
fun PrestamoCard(prestamo: Prestamo, navController: NavController) {
    StandardHomeCard(
        title = prestamo.nombre,
        description = prestamo.estado,
        imageUrl = prestamo.urlImagen,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable {
                navController.navigate("details/${prestamo.id}/prestamo")
            }
    )
}


@Composable
fun ObjetoCard(objeto: Objeto, navController: NavController) {
    MainTheme {
        StandardHomeCard(
            title = objeto.nombre,
            description = objeto.descripcion,
            imageUrl = objeto.urlImagen,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable {
                    navController.navigate("details/${objeto.id}/objeto")
                }
        )
    }
}
@Composable
fun NoticiaCard(noticia: Noticia, navController: NavController) {
    MainTheme {
        StandardHomeCard(
            title = noticia.titulo,
            description = noticia.descripcion,
            imageUrl = noticia.urlImagen,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable {
                    navController.navigate("details/${noticia.id}/noticia")
                }
        )
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    MainTheme {
        // Usa un LazyColumn para permitir scroll vertical
        LazyColumn(
            modifier = Modifier
                .fillMaxSize() // Ajusta el tamaño para ocupar la pantalla completa
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Encabezados no scrolleables
            item {
                GreetingSection()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SearchSection()
            }

            // Secciones scrolleables horizontalmente
            item {
                PrestamosSection(prestamos, navController)
            }
            item {
                ObjetosSection(objetos, navController)
            }
            item {
                NoticiasSection(noticias, navController)
            }
        }
    }
}

@Composable
fun PrestamosSection(prestamos: List<Prestamo>, navController: NavController) {
    MainTheme {
        Column {
            Text(
                text = "Últimos Préstamos",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Usa LazyRow para scroll horizontal
            LazyRow(
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(prestamos) { prestamo ->
                    PrestamoCard(prestamo = prestamo, navController = navController)
                }
            }
        }
    }
}

@Composable
fun ObjetosSection(objetos: List<Objeto>, navController: NavController) {
    MainTheme {
        Column {
            Text(
                text = "Objetos Populares",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Usa LazyRow para scroll horizontal
            LazyRow(
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(objetos) { objeto ->
                    ObjetoCard(objeto = objeto, navController = navController)
                }
            }
        }
    }
}

@Composable
fun NoticiasSection(noticias: List<Noticia>, navController: NavController) {
    MainTheme {
        Column {
            Text(
                text = "Noticias",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Usa LazyRow para scroll horizontal
            LazyRow(
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(noticias) { noticia ->
                    NoticiaCard(noticia = noticia, navController = navController)
                }
            }
        }
    }
}

@Composable
fun GreetingSection() {
    MainTheme {
        Text(
            text = "Hola Juan Ramirez 👋",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Bienvenido a CEIIT InvMgr",
            style = MaterialTheme.typography.subtitle1,
            color = Color.Gray
        )
    }
}

@Composable
fun SearchSection() {
    MainTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                placeholder = { Text("Buscar") },
                leadingIcon = {
                    Icon(Icons.Filled.Search, contentDescription = "Buscar")
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFF3F3F3),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController())
}

