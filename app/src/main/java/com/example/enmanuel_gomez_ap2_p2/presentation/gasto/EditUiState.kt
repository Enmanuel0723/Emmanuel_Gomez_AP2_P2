package com.example.enmanuel_gomez_ap2_p2.presentation.gasto

data class EditUiState(
    val gastoId: Int = 0,
    val fecha: String = "",
    val suplidor: String = "",
    val ncf: String = "",
    val itbis: String = "",
    val monto: String = "",
    val suplidorError: String? = null,
    val montoError: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val saved: Boolean = false
)
