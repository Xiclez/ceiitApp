import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Inventory : Screen("inventory", "Inventario", Icons.Filled.List)
    object Scanner : Screen("scanner", "Escanear", Icons.Filled.CameraAlt)
    object Profile : Screen("profile", "Perfil", Icons.Filled.Person)
}
