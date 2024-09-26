package com.example.ceiitApp.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.ceiitApp.R
import com.example.ceiitApp.themes.MainTheme

@Composable
fun ProfileScreen() {
    MainTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Perfil",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileSection()
            Spacer(modifier = Modifier.height(16.dp))
            MenuOptions()
        }
    }
}
@Composable
fun ProfileSection() {
    MainTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp)
        ) {
            // Placeholder for QR code
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "QR Code",
                modifier = Modifier.size(150.dp)
            )
            // Profile image with camera icon
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = rememberImagePainter("https://via.placeholder.com/150"),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = { /* Cambiar foto */ },
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.White, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Cambiar foto",
                        tint = Color.Blue
                    )
                }
            }
        }
        Text(
            text = "Juan Ramirez",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = "3422",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}
@Composable
fun MenuOptions() {
    MainTheme {
        Column {
            MenuItem(icon = Icons.Default.Person, text = "Mis tokens")
            MenuItem(icon = Icons.Default.Person, text = "Historial de eventos")
            MenuItem(icon = Icons.Default.Person, text = "Historial de recompensas")
            MenuItem(icon = Icons.Default.Person, text = "Favoritos")
            MenuItem(icon = Icons.Default.Person, text = "Mi información")
        }
    }
}
@Composable
fun MenuItem(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    MainTheme {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = Color.Blue)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = text, fontWeight = FontWeight.Bold)
            }
        }
    }
}
@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}
