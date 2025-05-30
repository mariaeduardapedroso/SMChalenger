package com.example.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.text.style.TextAlign

@Composable
fun RecipeScreen(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = viewModel()
    val state by viewModel.state

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = viewModel.searchQuery,
            onValueChange = viewModel::onSearchQueryChanged,
            label = { Text("Search name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        OutlinedTextField(
            value = viewModel.temperamentQuery,
            onValueChange = viewModel::onTemperamentQueryChanged,
            label = { Text("Search temperament") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.loading -> CircularProgressIndicator(modifier.align(Alignment.Center))

                state.error != null -> Text("Erro: ${state.error}", modifier.align(Alignment.Center))

                else -> BreedList(breeds = state.filteredBreeds)
            }
        }
    }
}

@Composable
fun BreedList(breeds: List<Breed>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) {
        items(breeds) { breed ->
            BreedItem(breed)
        }
    }
}

@Composable
fun BreedItem(breed: Breed) {
    val imageUrl = breed.image?.url ?: breed.reference_image_id?.let {
        "https://cdn2.thecatapi.com/images/$it.jpg"
    }

    val painter = rememberAsyncImagePainter(model = imageUrl)
    val state = painter.state

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state is AsyncImagePainter.State.Error || imageUrl == null) {
                Image(
                    painter = painterResource(id = R.drawable.cat_not_found),
                    contentDescription = "Imagem não encontrada",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            } else {
                Image(
                    painter = painter,
                    contentDescription = "Imagem da raça ${breed.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = breed.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Ícone de localização",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = breed.origin,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = breed.temperament,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}