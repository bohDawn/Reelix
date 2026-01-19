package com.bohdawn.reelix.ui

import Movie
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DetailScreen(movie: Movie) {
    // 1. Головний контейнер із прокруткою
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E)) // Темний фон як у списку
            .verticalScroll(rememberScrollState()) // 👈 МАГІЯ: Дозволяє скролити екран
    ) {
        // 2. ВЕЛИКИЙ БАНЕР (Backdrop)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Високий банер
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = "Movie Banner",
                contentScale = ContentScale.Crop, // Обрізаємо, щоб заповнити ширину
                modifier = Modifier.fillMaxSize()
            )

            // Тут можна додати градієнт знизу, щоб текст читався краще (advanced level)
        }

        // 3. ІНФОРМАЦІЯ ПРО ФІЛЬМ
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Назва
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium, // Великий заголовок
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Рядок з мета-даними (Рейтинг, Рік, Жанр)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Зірочка
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFD700), // Золотий колір
                    modifier = Modifier.height(20.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                // Рейтинг
                Text(
                    text = "${movie.rating} / 10",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Рік (Фейковий поки що)
                Text(
                    text = "2024",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Секція "Storyline"
            Text(
                text = "Storyline",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Опис фільму (Lorem Ipsum)
            Text(
                text = "Eddie and Venom are on the run. Hunted by both of their worlds and with the net closing in, the duo are forced into a devastating decision that will bring the curtains down on Venom and Eddie's last dance. " +
                        "This is a long text to test the scrolling functionality of the screen. " +
                        "If you scroll down, you will see that the content moves up perfectly.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFFCCCCCC), // Світло-сірий текст (легше читати, ніж білий)
                lineHeight = 24.sp
            )
        }
    }
}


@Preview
@Composable
fun DetailScreenPreview() {
    // Створюємо фейковий фільм для тесту
    val sampleMovie = Movie(
        id = 1,
        title = "Venom: The Last Dance",
        posterUrl = "https://image.tmdb.org/t/p/w500/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
        rating = 8.5
    )

    DetailScreen(movie = sampleMovie)
}