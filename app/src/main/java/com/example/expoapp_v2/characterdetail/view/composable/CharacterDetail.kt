package com.example.expoapp_v2.characterdetail.view.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.expoapp_v2.characterdetail.domain.model.DetailedCharacter
import com.example.expoapp_v2.characterdetail.viewmodel.CharacterDetailViewModel
import com.example.expoapp_v2.common.service.ApiResult
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CharacterDetail(
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val isRefreshingState by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val characterState by viewModel.character.collectAsStateWithLifecycle()
    CharacterScreen(
        modifier = modifier,
        isRefreshingState = isRefreshingState,
        characterState = characterState,
        refreshPage = { viewModel.refresh(viewModel.characterId) },
        onBackClick = onBackClick
    )
}

@Composable
fun CharacterScreen(
    modifier: Modifier,
    refreshPage: () -> Unit,
    isRefreshingState: Boolean,
    characterState: ApiResult<DetailedCharacter>,
    onBackClick: () -> Unit,
) {
    when (characterState) {
        is ApiResult.Success -> {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshingState),
                onRefresh = {
                    refreshPage.invoke()
                }) {
                CharacterDetail(characterItem = characterState.data, onBackClick = onBackClick)
            }
        }
        is ApiResult.Error -> {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshingState),
                onRefresh = {
                    refreshPage.invoke()
                }) {
                Error(
                    modifier = modifier,
                    errorMessage = characterState.exception.message
                        ?: "Unknown error, please refresh"
                )
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
fun CharacterDetail(
    characterItem: DetailedCharacter,
    onBackClick: () -> Unit
) {
    Column {
        Button(onClick = { onBackClick.invoke() }) {
            Text(text = "Go back")
        }
        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(vertical = 8.dp))
                .clickable(
                    onClick = { onBackClick.invoke() }
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
        Divider(color = Color.Black)
        Text(text = characterItem.episode.toString())
    }

}

@Composable
fun Error(
    modifier: Modifier,
    errorMessage: String
) {
    Row(modifier) {
        Text(text = errorMessage, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}
