package com.example.enmanuel_gomez_ap2_p2.presentation.entidad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enmanuel_gomez_ap2_p2.data.remote.dto.EntidadDto
import com.example.enmanuel_gomez_ap2_p2.domain.repository.EntidadRepository
import com.example.enmanuel_gomez_ap2_p2.domain.usecases.SaveEntidadUseCase
import com.example.enmanuel_gomez_ap2_p2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EditUiState(
    val entidadId: Int = 0,
    val nombre: String = "",
    val error: String? = null,
    val saved: Boolean = false
)

@HiltViewModel
class EditViewModel @Inject constructor(
    private val saveEntidadUseCase: SaveEntidadUseCase,
    private val repository: EntidadRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditUiState())
    val uiState: StateFlow<EditUiState> = _uiState.asStateFlow()

    fun loadEntidad(id: Int) {
        viewModelScope.launch {
            when (val result = repository.getEntidadById(id)) {
                is Resource.Success -> result.data?.let { entidad ->
                    _uiState.update {
                        it.copy(entidadId = entidad.entidadId, nombre = entidad.nombre)
                    }
                }
                is Resource.Error -> _uiState.update { it.copy(error = result.message) }
                is Resource.Loading -> Unit
            }
        }
    }

    fun onNombreChange(value: String) {
        _uiState.update { it.copy(nombre = value, error = null) }
    }

    fun save() {
        if (_uiState.value.nombre.isBlank()) {
            _uiState.update { it.copy(error = "El nombre no puede estar vacío") }
            return
        }
        viewModelScope.launch {
            val entidad = EntidadDto(
                entidadId = _uiState.value.entidadId,
                nombre = _uiState.value.nombre.trim()
            )
            when (val result = saveEntidadUseCase(entidad)) {
                is Resource.Success -> _uiState.update { it.copy(saved = true) }
                is Resource.Error -> _uiState.update { it.copy(error = result.message) }
                is Resource.Loading -> Unit
            }
        }
    }
}
