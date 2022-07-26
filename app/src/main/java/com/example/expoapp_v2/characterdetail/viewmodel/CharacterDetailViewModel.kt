package com.example.expoapp_v2.characterdetail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expoapp_v2.characterdetail.domain.ExtractEpisodeIdFromUrlUseCase
import com.example.expoapp_v2.characterdetail.domain.GetCharacterDetailUseCase
import com.example.expoapp_v2.characterdetail.domain.GetEpisodeDetailUseCase
import com.example.expoapp_v2.characterdetail.domain.model.DetailedCharacter
import com.example.expoapp_v2.characterdetail.domain.model.DetailedEpisode
import com.example.expoapp_v2.characterdetail.view.CharacterDetailDestination
import com.example.expoapp_v2.common.service.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    private val getEpisodeDetailUseCase: GetEpisodeDetailUseCase,
    private val extractEpisodeIdFromUrlUseCase: ExtractEpisodeIdFromUrlUseCase,
) : ViewModel() {

    val characterId: Int = checkNotNull(
        savedStateHandle[CharacterDetailDestination.characterId]
    )

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _character = MutableStateFlow<ApiResult<DetailedCharacter>>(ApiResult.Loading)
    val character: StateFlow<ApiResult<DetailedCharacter>>
        get() = _character.asStateFlow()

    private val _episodes = MutableStateFlow<List<ApiResult<DetailedEpisode>>>(emptyList())
    val episodes: StateFlow<List<ApiResult<DetailedEpisode>>>
        get() = _episodes.asStateFlow()

    init {
        viewModelScope.launch {
            getScreenData(characterId)
        }
    }

    fun refresh(id: Int) {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            getScreenData(id)
            _isRefreshing.emit(false)
        }
    }

    fun loadEpisodeData(episodes: List<String>) {
        val episodesFlows = mutableListOf<Flow<ApiResult<DetailedEpisode>>>()
        for (episodeURL in episodes) {
            episodesFlows.add(
                getEpisodeDetailUseCase(
                    GetEpisodeDetailUseCase.GetEpisodeDetailUseCaseParams(
                        extractEpisodeIdFromUrlUseCase(episodeURL)
                    )
                )
            )
        }
        viewModelScope.launch {
            combine(
                flows = episodesFlows
            ) {
                it
            }.collect {
                _episodes.emit(it.toList())
            }
        }
    }

    private suspend fun getScreenData(id: Int) {
        getCharacterDetailUseCase(GetCharacterDetailUseCase.GetCharacterUseCaseParams(id)).collect {
            _character.value = it
        }
    }
}