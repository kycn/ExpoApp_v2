package com.example.expoapp_v2.characterdetail.view.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.expoapp_v2.characterdetail.domain.model.DetailedEpisode
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
        loadEpisodes = { viewModel.loadEpisodeData(it) },
        onBackClick = onBackClick
    )
}

@Composable
fun CharacterScreen(
    modifier: Modifier,
    refreshPage: () -> Unit,
    loadEpisodes: (List<String>) -> Unit,
    isRefreshingState: Boolean,
    characterState: ApiResult<DetailedCharacter>,
    onBackClick: () -> Unit,
) {
    when (characterState) {
        is ApiResult.Success -> {
            loadEpisodes.invoke(characterState.data.episode)
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshingState),
                onRefresh = {
                    refreshPage.invoke()
                }) {
                CharacterDetail(
                    characterItem = characterState.data,
                    onBackClick = onBackClick
                )
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
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(8.dp)),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = { onBackClick.invoke() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back image")
        }
        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .padding(PaddingValues(vertical = 8.dp)),
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
        Text(text = "Episodes", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        EpisodesSection(modifier = Modifier)
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun EpisodesSection(
    modifier: Modifier,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val episodeState by viewModel.episodes.collectAsStateWithLifecycle()
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(episodeState) {
            when (it) {
                is ApiResult.Success -> {
                    EpisodeDetail(modifier = modifier, detailedEpisode = it.data)
                }
                is ApiResult.Error -> {
                    Error(
                        modifier = Modifier,
                        errorMessage = it.exception.message
                            ?: "Unknown error, please refresh"
                    )
                }
                ApiResult.Loading -> {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(Modifier)
                    }
                }
            }
        }
    }

}

@Composable
fun EpisodeDetail(modifier: Modifier, detailedEpisode: DetailedEpisode) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Black))
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = detailedEpisode.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = detailedEpisode.episodeName)
        Text(text = detailedEpisode.airData)
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
