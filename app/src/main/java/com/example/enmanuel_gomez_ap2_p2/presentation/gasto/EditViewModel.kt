package com.example.enmanuel_gomez_ap2_p2.presentation.gasto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enmanuel_gomez_ap2_p2.domain.model.Gasto
import com.example.enmanuel_gomez_ap2_p2.domain.usecases.GetGastoByIdUseCase
import com.example.enmanuel_gomez_ap2_p2.domain.usecases.SaveGastoUseCase
import com.example.enmanuel_gomez_ap2_p2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val getGastoByIdUseCase: GetGastoByIdUseCase,
    private val saveGastoUseCase: SaveGastoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        EditUiState(fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")))
    )
    val uiState: StateFlow<EditUiState> = _uiState.asStateFlow()

    fun load(gastoId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = getGastoByIdUseCase(gastoId)) {
                is Resource.Success -> result.data?.let { gasto ->
                    _uiState.update {
                        it.copy(
                            gastoId = gasto.gastoId,
                            fecha = gasto.fecha,
                            suplidor = gasto.suplidor,
                            ncf = gasto.ncf,
                            itbis = gasto.itbis.toString(),
                            monto = gasto.monto.toString(),
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> _uiState.update { it.copy(error = result.message, isLoading = false) }
                is Resource.Loading -> Unit
            }
        }
    }

    fun onEvent(event: EditUiEvent) {
        when (event) {
            is EditUiEvent.OnFechaChanged -> _uiState.update { it.copy(fecha = event.value) }
            is EditUiEvent.OnSuplidorChanged -> _uiState.update { it.copy(suplidor = event.value, suplidorError = null) }
            is EditUiEvent.OnNcfChanged -> _uiState.update { it.copy(ncf = event.value) }
            is EditUiEvent.OnItbisChanged -> _uiState.update { it.copy(itbis = event.value) }
            is EditUiEvent.OnMontoChanged -> _uiState.update { it.copy(monto = event.value, montoError = null) }
            is EditUiEvent.OnSave -> save()
        }
    }

    private fun save() {
        val state = _uiState.value
        var hasError = false

        if (state.suplidor.isBlank()) {
            _uiState.update { it.copy(suplidorError = "El suplidor no puede estar vacío") }
            hasError = true
        }

        val montoValue = state.monto.toDoubleOrNull()
        if (montoValue == null || montoValue <= 0) {
            _uiState.update { it.copy(montoError = "El monto debe ser mayor a 0") }
            hasError = true
        }

        if (hasError) return

        viewModelScope.launch {
            val gasto = Gasto(
                gastoId = state.gastoId,
                fecha = state.fecha,
                suplidor = state.suplidor.trim(),
                ncf = state.ncf,
                itbis = state.itbis.toDoubleOrNull() ?: 0.0,
                monto = montoValue!!
            )
            when (val result = saveGastoUseCase(gasto)) {
                is Resource.Success -> _uiState.update { it.copy(saved = true) }
                is Resource.Error -> _uiState.update { it.copy(error = result.message) }
                is Resource.Loading -> Unit
            }
        }
    }
}
