package com.example.ceiitApp.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import coil.compose.rememberImagePainter
import com.example.ceiitApp.models.Objeto

@Composable
fun InventoryScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Inventario",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InventoryList(objects = getDummyData(), navController = navController)
    }
}

@Composable
fun InventoryList(objects: List<Objeto>, navController: NavController) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(objects) { objeto ->
            InventoryItem(objeto = objeto, navController = navController)
        }
    }
}

@Composable
fun InventoryItem(objeto: Objeto, navController: NavController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                navController.navigate("details/${objeto.id}/objeto")
            },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(objeto.urlImagen),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(150.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = objeto.nombre,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = objeto.descripcion,
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "Estado: ${objeto.estado}",
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                    color = if (objeto.estado == "Disponible") MaterialTheme.colors.primary else MaterialTheme.colors.error
                )
            }
        }
    }
}

fun getDummyData(): List<Objeto> {
    return listOf(
        Objeto(
            id = "1",
            nombre = "SIERRA DE MESA RYOBI",
            descripcion = "Sierra de mesa para trabajos de carpintería.",
            estado = "Disponible",
            categoria = "Herramientas",
            urlImagen = "https://cdn.homedepot.com.mx/productos/133485/133485-d.jpg",
            ubicacion = "Taller de Madera y Metal",
            qr = "testqr"
        ),
        Objeto(
            id = "2",
            nombre = "TORNO DE MADERA KNOVA",
            descripcion = "Torno de madera para trabajos precisos.",
            estado = "Disponible",
            categoria = "Herramientas",
            urlImagen = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFd2PLKOm-wZl2e3xD57rL-_D8IENvasFQGQ&s",
            ubicacion = "Taller de Madera y Metal",
            qr = "testqr"
        ),
        Objeto(
            id = "3",
            nombre = "IMPRESORA 3D CREALITY",
            descripcion = "Impresora 3D para prototipado rápido.",
            estado = "En Uso",
            categoria = "Impresoras",
            urlImagen = "https://www.3dmarket.mx/wp-content/uploads/2022/07/Impresora-3D-Ender-3-S1-Creality.webp",
            ubicacion = "FabLab",
            qr = "testqr"
        ),
        Objeto(
            id = "4",
            nombre = "SUBLIMADORA CACHUCHAS",
            descripcion = "Máquina de sublimación para gorras.",
            estado = "Disponible",
            categoria = "Sublimación",
            urlImagen = "https://resources.sears.com.mx/medios-plazavip/mkt/6109c9f131422_gorrajpg.jpg",
            ubicacion = "FabLab",
            qr = "testqr"
        ),
        Objeto(
            id = "5",
            nombre = "TALADRO INALÁMBRICO",
            descripcion = "Taladro inalámbrico de alta potencia.",
            estado = "Disponible",
            categoria = "Herramientas",
            urlImagen = "https://static.grainger.com/rp/s/is/image/Grainger/22UT50_AS02?",
            ubicacion = "Taller de Madera y Metal",
            qr = "testqr"
        ),
        Objeto(
            id = "6",
            nombre = "LÁSER DE CORTE",
            descripcion = "Máquina láser para corte de precisión.",
            estado = "En Mantenimiento",
            categoria = "Máquinas",
            urlImagen = "https://m.media-amazon.com/images/I/71zrbVEpAaL.jpg",
            ubicacion = "FabLab",
            qr = "testqr"
        ),
        Objeto(
            id = "7",
            nombre = "Raspberry Pi 3 Model B+",
            descripcion = "Computadora compacta para proyectos de IoT.",
            estado = "Disponible",
            categoria = "Electrónica",
            urlImagen = "https://m.media-amazon.com/images/I/71nhCFbdy0L.jpg",
            ubicacion = "Oficina",
            qr = "testqr"
        ),
        Objeto(
            id = "8",
            nombre = "Protoboard",
            descripcion = "Tablilla de pruebas para prototipado de circuitos de 830 puntos.",
            estado = "Disponible",
            categoria = "Electrónica",
            urlImagen = "https://aelectronics.com.mx/893/protoboard-blanca-de-830-puntos.jpg",
            ubicacion = "Oficina",
            qr = "testqr"
        ),
        Objeto(
            id = "9",
            nombre = "PROYECTOR EPSON",
            descripcion = "Proyector de alta definición para presentaciones.",
            estado = "En Uso",
            categoria = "Audiovisuales",
            urlImagen = "https://mediaserver.goepson.com/ImConvServlet/imconv/93ca5c94893d43c0e0512ff54ce6f5b624db3707/1200Wx1200H?use=banner&hybrisId=B2C&assetDescr=V11H569020_Proyectores_Epson%20PowerLite%20X17_ES",
            ubicacion = "Oficina",
            qr = "testqr"
        ),
        Objeto(
            id = "10",
            nombre = "MESA DE CORTE",
            descripcion = "Mesa de corte para trabajos grandes.",
            estado = "No Disponible",
            categoria = "Herramientas",
            urlImagen = "https://res.cloudinary.com/dn4m0kr7j/image/upload/v1719868792/logoInvMgr_ongzfh.png",
            ubicacion = "Taller de Madera y Metal",
            qr = "testqr"
        )
    )
}

@Composable
@Preview
fun PreviewInventoryScreen() {
    InventoryScreen(navController = rememberNavController())
}
