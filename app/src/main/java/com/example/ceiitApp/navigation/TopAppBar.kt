import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ceiitApp.themes.MainTheme

@Composable
fun TopAppBar(title: String) {
    MainTheme {
        TopAppBar(
            title = {
                Text(text = title)
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            elevation = 12.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}