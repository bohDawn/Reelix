import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            // Додаємо рамку або фон, щоб це виглядало як картка
            .background(androidx.compose.ui.graphics.Color.White)
    ) {
        // 1. Місце під картинку (Заглушка)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(androidx.compose.ui.graphics.Color(0xFFE0E0E0)), // Світло-сірий
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text("POSTER", color = androidx.compose.ui.graphics.Color.Gray)
        }

        // 2. Інформація (Тексти)
        Column(
            modifier = Modifier.padding(12.dp) // Відступ тексту всередині картки
        ) {
            // Назва фільму
            Text(
                text = movie.title,
                fontSize = 20.sp, // Більший шрифт
                fontWeight = FontWeight.Bold, // Жирний
                color = androidx.compose.ui.graphics.Color.Black
            )

            // Рейтинг і жанри (імітація)
            Text(
                text = "⭐ ${movie.rating} • Action, Sci-Fi",
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.DarkGray,
                modifier = Modifier.padding(top = 4.dp) // Відступ від назви
            )
        }
    }
}