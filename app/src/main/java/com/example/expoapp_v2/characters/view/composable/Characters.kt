package com.example.expoapp_v2.characters.view.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.expoapp_v2.characters.domain.model.CharacterItem
import com.example.expoapp_v2.characters.viewmodel.CharactersViewModel
import com.example.expoapp_v2.common.service.ApiResult
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Characters(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel(),
    navigateToProductDetail: (CharacterItem) -> Unit
) {
    val isRefreshingState by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val charactersState by viewModel.characters.collectAsStateWithLifecycle()
    CharactersScreen(
        modifier = modifier,
        isRefreshingState = isRefreshingState,
        charactersState = charactersState,
        refreshPage = { viewModel.refresh() },
        navigateToProductDetail = navigateToProductDetail
    )
}

@Composable
fun CharactersScreen(
    modifier: Modifier,
    refreshPage: () -> Unit,
    isRefreshingState: Boolean,
    charactersState: ApiResult<List<CharacterItem>>,
    navigateToProductDetail: (CharacterItem) -> Unit,
) {
    when (charactersState) {
        is ApiResult.Success -> {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshingState),
                onRefresh = {
                    refreshPage.invoke()
                }) {
                LazyColumn(
                    modifier = modifier,
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(charactersState.data) { character ->
                        CharacterItemRow(character, navigateToProductDetail)
                        Divider(color = Color.Black)
                    }
                }
            }
        }
        is ApiResult.Error -> {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshingState),
                onRefresh = {
                    refreshPage.invoke()
                }) {
                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        ErrorItemRow(
                            modifier = modifier,
                            errorMessage = charactersState.exception.message
                                ?: "Unknown error, please refresh"
                        )
                    }
                }
            }
        }
        ApiResult.Loading -> {
            Row(
                modifier = modifier.fillMaxSize(),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(modifier)
            }
        }
    }
}


@Composable
fun CharacterItemRow(
    characterItem: CharacterItem,
    navigateToProductDetail: (CharacterItem) -> Unit
) {
    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(vertical = 8.dp))
            .clickable(
                onClick = { navigateToProductDetail.invoke(characterItem) }
            ),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = characterItem.image,
            contentDescription = "Character image",
            contentScale = ContentScale.Crop,
        )
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row {
                Text(text = characterItem.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Row {
                Text(text = "Status: ", fontWeight = FontWeight.Bold)
                Text(text = characterItem.status)
            }
            Row {
                Text(text = "Species: ", fontWeight = FontWeight.Bold)
                Text(text = characterItem.species)
            }
            Row {
                Text(text = "Gender: ", fontWeight = FontWeight.Bold)
                Text(text = characterItem.gender)
            }
        }
    }
}

@Composable
fun ErrorItemRow(
    modifier: Modifier,
    errorMessage: String
) {
    Row(modifier) {
        Text(text = errorMessage, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}
