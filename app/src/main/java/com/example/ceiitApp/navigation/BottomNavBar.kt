import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ceiitApp.themes.MainTheme

@Composable
fun BottomNavigationBar(navController: NavController) {
    MainTheme {
        val items = listOf(
            Screen.Home,
            Screen.Inventory,
            Screen.Scanner,
            Screen.Profile
        )

        BottomNavigation(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White
        ) {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route

            items.forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(screen.icon, contentDescription = screen.title) },
                    label = { Text(screen.title) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationRoute ?: screen.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}
@Preview
@Composable
fun PreviewBottomNavigationBar() {
    val navController = rememberNavController()
    BottomNavigationBar(navController)
}
