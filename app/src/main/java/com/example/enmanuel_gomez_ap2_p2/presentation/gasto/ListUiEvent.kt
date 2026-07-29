package com.example.enmanuel_gomez_ap2_p2.presentation.gasto

sealed interface ListUiEvent {
    data object OnAddClick : ListUiEvent
    data class OnGastoClick(val gastoId: Int) : ListUiEvent
}
