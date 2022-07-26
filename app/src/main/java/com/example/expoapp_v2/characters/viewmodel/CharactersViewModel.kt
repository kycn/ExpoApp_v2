package com.example.expoapp_v2.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expoapp_v2.characters.domain.GetCharactersUseCase
import com.example.expoapp_v2.characters.domain.model.CharacterItem
import com.example.expoapp_v2.common.service.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _characters = MutableStateFlow<ApiResult<List<CharacterItem>>>(ApiResult.Loading)
    val characters: StateFlow<ApiResult<List<CharacterItem>>>
        get() = _characters.asStateFlow()

    init {
        viewModelScope.launch {
            getScreenData()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            // A fake 2 second 'refresh'
            _isRefreshing.emit(true)
            getScreenData()
            _isRefreshing.emit(false)
        }
    }

    private suspend fun getScreenData() {
        getCharactersUseCase(Unit).collect {
            _characters.value = it
        }
    }
}