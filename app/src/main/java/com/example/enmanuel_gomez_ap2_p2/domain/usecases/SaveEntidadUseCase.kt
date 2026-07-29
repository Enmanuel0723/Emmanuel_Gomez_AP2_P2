package com.example.enmanuel_gomez_ap2_p2.domain.usecases

import com.example.enmanuel_gomez_ap2_p2.data.remote.dto.EntidadDto
import com.example.enmanuel_gomez_ap2_p2.domain.repository.EntidadRepository
import javax.inject.Inject

class SaveEntidadUseCase @Inject constructor(
    private val repository: EntidadRepository
) {
    suspend operator fun invoke(entidad: EntidadDto) = repository.saveEntidad(entidad)
}
