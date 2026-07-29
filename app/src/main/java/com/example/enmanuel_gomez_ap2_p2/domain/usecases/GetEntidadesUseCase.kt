package com.example.enmanuel_gomez_ap2_p2.domain.usecases

import com.example.enmanuel_gomez_ap2_p2.domain.repository.EntidadRepository
import javax.inject.Inject

class GetEntidadesUseCase @Inject constructor(
    private val repository: EntidadRepository
) {
    operator fun invoke() = repository.getEntidades()
}
