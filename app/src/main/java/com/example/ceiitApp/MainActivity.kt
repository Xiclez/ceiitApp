package com.example.ceiitApp

import BottomNavigationBar
import TopAppBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.ceiitApp.*
import com.example.ceiitApp.navigation.AppNavGraph

//import com.example.ceiitApp.ui.theme.MyAppTheme // Tu tema personalizado (si lo tienes configurado)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           // MyAppTheme {
                // Llamada a la función que define la UI principal
                MainScreen()
           //  }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding -> // Aquí estamos recibiendo el padding
        // Pasamos el padding a AppNavGraph
        AppNavGraph(navController = navController, padding = innerPadding)
    }
}


@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
