import coil.compose.AsyncImage
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.background

@Composable
fun StandardHomeCard(
    title: String,
    description: String,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        elevation = 4.dp
    ) {
        Box {
            // Ajusta la imagen al tamaño de la tarjeta sin recortarla
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds // Ajusta la imagen sin recortarla
            )

            // Banner gris semitransparente para el texto
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(Color.Black.copy(alpha = 0.6f))
                    .padding(8.dp)
            ) {
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = description,
                        style = MaterialTheme.typography.body2,
                        color = Color.White
                    )
                }
            }
        }
    }
}
