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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.blur
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bohdawn.reelix.viewmodels.HomeViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import com.bohdawn.reelix.dependency_injection.appModule
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication
import org.koin.dsl.koinConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    KoinApplication(configuration = koinConfiguration(declaration = fun KoinApplication.() {
        modules(appModule)
    }), content = {
            val viewModel: HomeViewModel = koinViewModel()

            val movies by viewModel.moviesList.collectAsState()
            val selectedMovie by viewModel.selectedMovie.collectAsState()

            val blurAmount by animateDpAsState(
                targetValue = if (selectedMovie != null) 10.dp else 0.dp,
                label = "blur_animation"
            )

            MaterialTheme {
                Scaffold(
                    modifier = Modifier.blur(blurAmount),

                    ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF121212))
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            itemsIndexed(movies) { index, movie ->

                                MovieItem(movie = movie,
                                    onClick = { viewModel.selectMovie(movie)})

                                if (index >= movies.size - 3) {
                                    LaunchedEffect(key1 = Unit) {
                                        viewModel.loadMovies()
                                    }
                                }
                            }
                        }
                    }
                }

                selectedMovie?.let { movie ->
                    Dialog(
                        onDismissRequest = { viewModel.closeDetails()},
                        properties = DialogProperties(usePlatformDefaultWidth = false)
                    ) {
                        Box(modifier = Modifier.padding(24.dp)) {
                            DetailScreen(movie = movie)
                        }
                    }
                }
            }
        })
}