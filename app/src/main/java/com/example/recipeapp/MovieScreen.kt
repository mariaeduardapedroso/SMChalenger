package com.example.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.RatedMovie
import com.example.recipeapp.RatedTv

@Composable
fun MovieScreen(viewModel: FilmeViewModel = viewModel()) {
    var selectedTab by remember { mutableStateOf(0) } // 0 = Filmes, 1 = Séries

    val filmes by viewModel.filmesState
    val series by viewModel.tvSeriesState

    Column(modifier = Modifier.fillMaxSize()) {
        // Tabs para alternar entre filmes e séries
        TabRow(selectedTabIndex = selectedTab) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Movies") },
                icon = { Icon(Icons.Default.AccountBox, contentDescription = null) }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Series") },
                icon = { Icon(Icons.Default.Face, contentDescription = null) }
            )
        }

        // Exibir filmes ou séries com base na aba selecionada
        if (selectedTab == 0) {
            MovieList(filmes)
        } else {
            SerieList(series)
        }
    }
}

@Composable
fun MovieList(list: List<RatedMovie>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) { item ->
            MovieItem(item)
        }
    }
}

@Composable
fun SerieList(list: List<RatedTv>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) { item ->
            SerieItem(item)
        }
    }
}

@Composable
fun MovieItem(item: RatedMovie) {
    val imageUrl = "https://image.tmdb.org/t/p/w500${item.poster_path}"
    val painter = rememberAsyncImagePainter(model = imageUrl)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { /* Navegação para detalhes (opcional) */ },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painter,
                contentDescription = item.title,
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (item.overview.length > 180) item.overview.take(180) + "..." else item.overview,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun SerieItem(item: RatedTv) {
    val imageUrl = "https://image.tmdb.org/t/p/w500${item.poster_path}"
    val painter = rememberAsyncImagePainter(model = imageUrl)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { /* Navegação para detalhes (opcional) */ },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painter,
                contentDescription = item.name,
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (item.overview.length > 180) item.overview.take(180) + "..." else item.overview,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
