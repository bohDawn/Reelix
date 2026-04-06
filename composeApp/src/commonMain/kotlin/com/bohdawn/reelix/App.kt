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

val moviesList = List(20) { index ->
    // TODO: Replace mock data with real API response
    Movie(
        id = index,
        title = "Venom: Part ${index + 1}",
        posterUrl = "https://image.tmdb.org/t/p/w500/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
        rating = 8.0 + (index * 0.1)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }
    val blurAmount by animateDpAsState(
        targetValue = if (selectedMovie != null) 10.dp else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = "blur"
    )

    MaterialTheme(colorScheme = darkColorScheme()) {
        Scaffold(
            modifier = Modifier.blur(blurAmount),
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(moviesList) { movie ->
                    MovieItem(
                        movie = movie,
                        onClick = { selectedMovie = movie }
                    )
                }
            }

            if (selectedMovie != null) {
                Dialog(
                    properties = DialogProperties(usePlatformDefaultWidth = false),
                    onDismissRequest = { selectedMovie = null }
                ) {
                    Box(modifier = Modifier.padding(24.dp)){
                        DetailScreen(movie = selectedMovie!!)
                    }
                }
            }
        }
    }
}