package com.example.enmanuel_gomez_ap2_p2.presentation.gasto

sealed interface EditUiEvent {
    data class OnFechaChanged(val value: String) : EditUiEvent
    data class OnSuplidorChanged(val value: String) : EditUiEvent
    data class OnNcfChanged(val value: String) : EditUiEvent
    data class OnItbisChanged(val value: String) : EditUiEvent
    data class OnMontoChanged(val value: String) : EditUiEvent
    data object OnSave : EditUiEvent
}
