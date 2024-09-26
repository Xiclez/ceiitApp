package com.example.ceiitApp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ceiitApp.home.HomeScreen
import com.example.ceiitApp.inventory.InventoryScreen
import com.example.ceiitApp.scanner.ScannerScreen
import com.example.ceiitApp.profile.ProfileScreen
import com.example.ceiitApp.components.StandardDetailScreen

@Composable
fun AppNavGraph(navController: NavHostController, padding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(padding) // Aplicamos el padding
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Inventory.route) {
            InventoryScreen()
        }
        composable(Screen.Scanner.route) {
            ScannerScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
        composable(
            route = "details/{nombre}/{estado}/{urlImagen}",
            arguments = listOf(
                navArgument("nombre") { type = androidx.navigation.NavType.StringType },
                navArgument("estado") { type = androidx.navigation.NavType.StringType },
                navArgument("urlImagen") { type = androidx.navigation.NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val estado = backStackEntry.arguments?.getString("estado") ?: ""
            val urlImagen = backStackEntry.arguments?.getString("urlImagen") ?: ""
            StandardDetailScreen(
                title = nombre,
                description = estado,
                imageUrl = urlImagen,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
