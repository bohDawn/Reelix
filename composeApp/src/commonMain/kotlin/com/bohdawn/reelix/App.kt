import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.unit.dp
import com.bohdawn.reelix.ui.DetailScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

val testMovie = Movie(
    id = 1,
    title = "Venom: The Last Dance",
    posterUrl = "", // Поки що пустий URL, бо ми вимкнули картинки
    rating = 8.5
)

val moviesList = List(20) { index ->
    Movie(
        id = index,
        title = "Venom: Part ${index + 1}", // Буде Venom 1, Venom 2...
        // 👇 Встав сюди робоче посилання!
        posterUrl = "https://image.tmdb.org/t/p/w500/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
        rating = 8.0 + (index * 0.1) // Рейтинг буде 8.0, 8.1...
    )
}

@Composable
@Preview
fun App() {
    MaterialTheme(colorScheme = darkColorScheme()) { // Темна тема для кінотеатру топ
        Surface(modifier = Modifier.fillMaxSize()) {
            val sampleMovie = Movie(
                id = 1,
                title = "Venom: The Last Dance",
                posterUrl = "https://image.tmdb.org/t/p/w500/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
                rating = 8.5
            )

            DetailScreen(movie = sampleMovie)

//            LazyVerticalGrid(
//                // 1. Скільки колонок?
//                // Adaptive(150.dp) означає: "Вмісти стільки колонок, скільки влізе,
//                // але кожна має бути не менше 150dp шириною".
//                columns = GridCells.Adaptive(minSize = 150.dp),
//
//                // 2. Відступи навколо всієї сітки (щоб не прилипало до країв екрана)
//                contentPadding = PaddingValues(14.dp),
//
//                // 3. Відступи між картками по вертикалі та горизонталі
//                verticalArrangement = Arrangement.spacedBy(10.dp),
//                horizontalArrangement = Arrangement.spacedBy(10.dp)
//
//            ) {
//                // 4. Малюємо елементи
//                items(moviesList) { movie ->
//                    MovieItem(
//                        movie = movie,
//                        // Можна додати клік (поки що просто лог)
//                        modifier = Modifier.clip(RoundedCornerShape(10.dp))
//                            .clickable {
//                                println("Clicked on ${movie.title}")
//                            }
//                    )
//                }
//            }
        }
    }
}