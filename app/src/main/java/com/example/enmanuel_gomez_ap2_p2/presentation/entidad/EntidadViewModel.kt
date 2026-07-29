package com.example.enmanuel_gomez_ap2_p2.presentation.entidad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enmanuel_gomez_ap2_p2.data.remote.dto.EntidadDto
import com.example.enmanuel_gomez_ap2_p2.domain.usecases.DeleteEntidadUseCase
import com.example.enmanuel_gomez_ap2_p2.domain.usecases.GetEntidadesUseCase
import com.example.enmanuel_gomez_ap2_p2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntidadViewModel @Inject constructor(
    getEntidadesUseCase: GetEntidadesUseCase,
    private val deleteEntidadUseCase: DeleteEntidadUseCase
) : ViewModel() {

    val entidades: StateFlow<Resource<List<EntidadDto>>> = getEntidadesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Resource.Loading()
        )

    fun delete(entidad: EntidadDto) {
        viewModelScope.launch {
            deleteEntidadUseCase(entidad)
        }
    }
}
