package com.example.enmanuel_gomez_ap2_p2.presentation.gasto

import com.example.enmanuel_gomez_ap2_p2.domain.model.Gasto

data class ListUiState(
    val gastos: List<Gasto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val totalRegistros: Int get() = gastos.size
    val totalMonto: Double get() = gastos.sumOf { it.monto }
}
