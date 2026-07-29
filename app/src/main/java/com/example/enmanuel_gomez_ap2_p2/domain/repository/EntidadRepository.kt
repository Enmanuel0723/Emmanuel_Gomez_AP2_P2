package com.example.enmanuel_gomez_ap2_p2.domain.repository

import com.example.enmanuel_gomez_ap2_p2.data.remote.dto.EntidadDto
import com.example.enmanuel_gomez_ap2_p2.util.Resource
import kotlinx.coroutines.flow.Flow

interface EntidadRepository {
    fun getEntidades(): Flow<Resource<List<EntidadDto>>>
    suspend fun getEntidadById(id: Int): Resource<EntidadDto>
    suspend fun saveEntidad(entidad: EntidadDto): Resource<EntidadDto>
    suspend fun deleteEntidad(entidad: EntidadDto): Resource<Unit>
}
