package com.example.enmanuel_gomez_ap2_p2.presentation.gasto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enmanuel_gomez_ap2_p2.domain.usecases.GetGastosUseCase
import com.example.enmanuel_gomez_ap2_p2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    getGastosUseCase: GetGastosUseCase
) : ViewModel() {

    val uiState: StateFlow<ListUiState> = getGastosUseCase()
        .map { resource ->
            when (resource) {
                is Resource.Loading -> ListUiState(isLoading = true)
                is Resource.Success -> ListUiState(gastos = resource.data ?: emptyList())
                is Resource.Error -> ListUiState(error = resource.message)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ListUiState(isLoading = true)
        )
}
