package com.example.enmanuel_gomez_ap2_p2.domain.usecases

import com.example.enmanuel_gomez_ap2_p2.domain.model.Gasto
import com.example.enmanuel_gomez_ap2_p2.domain.repository.GastoRepository
import javax.inject.Inject

class SaveGastoUseCase @Inject constructor(
    private val repository: GastoRepository
) {
    suspend operator fun invoke(gasto: Gasto) = repository.saveGasto(gasto)
}
