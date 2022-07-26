package com.example.expoapp_v2.characterdetail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expoapp_v2.characterdetail.domain.model.DetailedCharacter
import com.example.expoapp_v2.characterdetail.view.CharacterDestination
import com.example.expoapp_v2.characters.domain.GetCharacterUseCase
import com.example.expoapp_v2.common.service.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    val characterId: Int = checkNotNull(
        savedStateHandle[CharacterDestination.characterId]
    )

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _character = MutableStateFlow<ApiResult<DetailedCharacter>>(ApiResult.Loading)
    val character: StateFlow<ApiResult<DetailedCharacter>>
        get() = _character.asStateFlow()

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

    private suspend fun getScreenData(id: Int) {
        getCharacterUseCase(GetCharacterUseCase.GetCharacterUseCaseParams(id)).collect {
            _character.value = it
        }
    }
}