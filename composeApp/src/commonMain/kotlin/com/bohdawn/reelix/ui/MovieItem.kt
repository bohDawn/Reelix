import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
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
            .background(Color(0xFF1E1E1E))
    ) {
        // 1. Місце під картинку (Заглушка)
        AsyncImage(
            model = movie.posterUrl, // Сюди прилітає посилання https://...
            contentDescription = movie.title,
            contentScale = ContentScale.Crop, // Картинка заповнює весь простір
            placeholder = ColorPainter(Color(0xFF2A2A2A)), // Поки вантажиться - сірий
            error = ColorPainter(Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )

        // 2. Інформація (Тексти)
        Column(
            modifier = Modifier.padding(12.dp) // Відступ тексту всередині картки
        ) {
            // Назва фільму
            Text(
                text = movie.title,
                fontSize = 20.sp, // Більший шрифт
                fontWeight = FontWeight.Bold, // Жирний
                color = androidx.compose.ui.graphics.Color.White
            )

            // Рейтинг і жанри (імітація)
            Text(
                text = "⭐ ${movie.rating} • Action, Sci-Fi",
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.LightGray,
                modifier = Modifier.padding(top = 4.dp) // Відступ від назви
            )
        }
    }
}