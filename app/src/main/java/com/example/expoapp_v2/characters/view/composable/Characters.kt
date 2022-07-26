package com.example.expoapp_v2.characters.view.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
                    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 2.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(charactersState.data) { character ->
                        CharacterItemRow(character, navigateToProductDetail)
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
                    modifier = modifier,
                    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 2.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    item {
                        ErrorItemRow(
                            errorMessage = charactersState.exception.message
                                ?: "Unknown error, please refresh"
                        )
                    }
                }
            }
        }
        ApiResult.Loading -> {
            CircularProgressIndicator()
        }
    }
}


@Composable
fun CharacterItemRow(
    characterItem: CharacterItem,
    navigateToProductDetail: (CharacterItem) -> Unit
) {

}

@Composable
fun ErrorItemRow(
    errorMessage: String
) {

}
