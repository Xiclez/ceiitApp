package com.example.ceiitApp.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LightColors = lightColors(
    primary = Color(0xFF0A1F44), // Azul oscuro
    primaryVariant = Color(0xFF3C73DC), // Azul claro para botones y acciones
    secondary = Color(0xFFFF9100), // Naranja para tokens o destacados
    background = Color(0xFFFFFFFF), // Fondo blanco
    surface = Color(0xFFF4F4F4), // Gris claro para tarjetas y contenedores
    onPrimary = Color.White, // Texto blanco sobre el azul oscuro
    onSecondary = Color.Black, // Texto negro sobre naranja
    onBackground = Color.Black, // Texto negro sobre fondo blanco
    onSurface = Color.Black // Texto negro en superficies gris claro
)

private val DarkColors = darkColors(
    primary = Color(0xFF0A1F44),
    primaryVariant = Color(0xFF3C73DC),
    secondary = Color(0xFFFF9100),
    background = Color(0xFF151515),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colors = colors,
        typography = Typography(
            h4 = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color(0xFF0A1F44) // Azul oscuro para títulos
            ),
            body1 = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color(0xFF000000) // Texto normal en negro
            ),
            body2 = TextStyle(
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                color = Color(0xFF616161) // Texto ligero para descripciones en gris
            )
        ),
        shapes = Shapes(
            small = RoundedCornerShape(8.dp), // Bordes redondeados pequeños
            medium = RoundedCornerShape(16.dp), // Bordes medianos para tarjetas
            large = RoundedCornerShape(24.dp) // Bordes grandes para elementos más grandes
        ),
        content = content
    )
}
