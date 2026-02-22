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

import androidx.compose.material3.ExperimentalMaterial3Api

// А це для анімації розмиття
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.blur
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

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

//var selectedMovie by remember { mutableStateOf<Movie?>(null) }
//
//val blurAmount by animateDpAsState(
//    targetValue = if (selectedMovie != null) 10.dp else 0.dp,
//    animationSpec = tween(durationMillis = 300), // пів секунди на ефект
//    label = "blur"
//)



//@Composable
//@Preview
//fun App() {
//    MaterialTheme(colorScheme = darkColorScheme()) { // Темна тема для кінотеатру топ
//        Surface(modifier = Modifier.fillMaxSize()) {
//            val sampleMovie = Movie(
//                id = 1,
//                title = "Venom: The Last Dance",
//                posterUrl = "https://image.tmdb.org/t/p/w500/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
//                rating = 8.5
//            )
//
//            DetailScreen(movie = sampleMovie)

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
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
//    val moviesList = remember {
//        List(20) { index ->
//            Movie(
//                id = index,
//                title = "Venom: The Last Dance",
//                posterUrl = "https://image.tmdb.org/t/p/w500/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
//                rating = 8.5
//            )
//        }
//    }

    var selectedMovie by remember { mutableStateOf<Movie?>(null) }

    // ✨ Анімація розмиття:
    // Якщо фільм є -> розмиття 10.dp, якщо ні -> 0.dp
    val blurAmount by animateDpAsState(
        targetValue = if (selectedMovie != null) 10.dp else 0.dp,
        animationSpec = tween(durationMillis = 300), // пів секунди на ефект
        label = "blur"
    )

    MaterialTheme(colorScheme = darkColorScheme()) {
        // Ми застосовуємо .blur прямо до Scaffold (це весь наш екран)
        Scaffold(
            modifier = Modifier.blur(blurAmount), // 👈 ВАЖЛИВО: Розмиваємо весь задній план
        ) {
            LazyVerticalGrid(
//                 1. Скільки колонок?
                // Adaptive(150.dp) означає: "Вмісти стільки колонок, скільки влізе,
                // але кожна має бути не менше 150dp шириною".
                columns = GridCells.Adaptive(minSize = 150.dp),

                // 2. Відступи навколо всієї сітки (щоб не прилипало до країв екрана)
                contentPadding = PaddingValues(14.dp),

                // 3. Відступи між картками по вертикалі та горизонталі
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // 4. Малюємо елементи
                items(moviesList) { movie ->
                    MovieItem(
                        movie = movie,
                        onClick = { selectedMovie = movie }
                    )
                }
            }

            // 🪟 ДІАЛОГОВЕ ВІКНО (Малюється поверх всього, поза Scaffold)
            if (selectedMovie != null) {
                Dialog(
                    properties = DialogProperties(usePlatformDefaultWidth = false),
                    onDismissRequest = { selectedMovie = null } // Закрити при кліку на фон
                ) {
                    Box(modifier = Modifier.padding(24.dp)){
                    // Тут ми викликаємо наш екран деталей
                        DetailScreen(movie = selectedMovie!!)
                    }
                }
            }
        }
    }
}