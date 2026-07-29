package com.example.enmanuel_gomez_ap2_p2.domain.repository

import com.example.enmanuel_gomez_ap2_p2.domain.model.Gasto
import com.example.enmanuel_gomez_ap2_p2.util.Resource
import kotlinx.coroutines.flow.Flow

interface GastoRepository {
    fun getGastos(): Flow<Resource<List<Gasto>>>
    suspend fun getGastoById(id: Int): Resource<Gasto>
    suspend fun saveGasto(gasto: Gasto): Resource<Gasto>
}
