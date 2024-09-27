package com.example.ceiitApp.navigation

import StandardDetailScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ceiitApp.home.HomeScreen
import com.example.ceiitApp.home.noticias
import com.example.ceiitApp.home.objetos
import com.example.ceiitApp.home.prestamos
import com.example.ceiitApp.inventory.InventoryScreen
import com.example.ceiitApp.scanner.ScannerScreen
import com.example.ceiitApp.profile.ProfileScreen
import com.example.ceiitApp.loans.*

@Composable
fun AppNavGraph(navController: NavHostController, padding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(padding)
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Inventory.route) {
            InventoryScreen(navController = navController)
        }
        composable(Screen.Scanner.route) {
            ScannerScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
        composable(
            "details/{id}/{type}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("type") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val objectType = backStackEntry.arguments?.getString("type") ?: ""

            // Retrieve the object based on its id and type
            val selectedObject = when (objectType) {
                "prestamo" -> prestamos.find { it.id == id }
                "objeto" -> objetos.find { it.id == id }
                "noticia" -> noticias.find { it.id == id }
                else -> null
            }

            selectedObject?.let {
                StandardDetailScreen(
                    obj = it,
                    objectType = objectType,
                    onBackClick = { navController.popBackStack() },
                    onStartLoanClick = { navController.navigate("confirmacionPrestamo/$id") } // Navigate to loan confirmation
                )
            }
        }

        // Loan confirmation navigation
        composable(
            "confirmacionPrestamo/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val selectedObject = objetos.find { it.id == id }

            selectedObject?.let {
                ConfirmacionPrestamoScreen(
                    obj = it,
                    isForStudent = true, // First confirmation is for the student
                    onNextClick = { navController.navigate("firmaAlumno/$id") }
                )
            }
        }

        // Student signature navigation
        composable(
            "firmaAlumno/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""

            FirmaCanvasScreen(onSignatureSave = {
                navController.navigate("confirmacionEncargado/$id")
            })
        }

        // CEIIT manager confirmation navigation
        composable(
            "confirmacionEncargado/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val selectedObject = objetos.find { it.id == id }

            selectedObject?.let {
                ConfirmacionPrestamoScreen(
                    obj = it,
                    isForStudent = false, // Second confirmation is for the CEIIT manager
                    onNextClick = { navController.navigate("firmaEncargado/$id") }
                )
            }
        }

        // CEIIT manager signature navigation
        composable(
            "firmaEncargado/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            FirmaCanvasScreen(onSignatureSave = {
                // Prestamo completed logic, maybe pop back to home or show confirmation
                navController.popBackStack(Screen.Home.route, inclusive = false)
            })
        }
    }
}
