package com.example.enmanuel_gomez_ap2_p2.data.repository

import com.example.enmanuel_gomez_ap2_p2.data.remote.api.GastoApi
import com.example.enmanuel_gomez_ap2_p2.data.remote.mapper.toDomain
import com.example.enmanuel_gomez_ap2_p2.data.remote.mapper.toRequest
import com.example.enmanuel_gomez_ap2_p2.domain.model.Gasto
import com.example.enmanuel_gomez_ap2_p2.domain.repository.GastoRepository
import com.example.enmanuel_gomez_ap2_p2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GastoRepositoryImpl @Inject constructor(
    private val api: GastoApi
) : GastoRepository {

    override fun getGastos(): Flow<Resource<List<Gasto>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getGastos()
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()?.map { it.toDomain() } ?: emptyList()))
            } else {
                emit(Resource.Error("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error desconocido"))
        }
    }

    override suspend fun getGastoById(id: Int): Resource<Gasto> {
        return try {
            val response = api.getGastoById(id)
            if (response.isSuccessful) {
                Resource.Success(response.body()!!.toDomain())
            } else {
                Resource.Error("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error desconocido")
        }
    }

    override suspend fun saveGasto(gasto: Gasto): Resource<Gasto> {
        return try {
            val response = if (gasto.gastoId == 0) {
                api.saveGasto(gasto.toRequest())
            } else {
                api.updateGasto(gasto.gastoId, gasto.toRequest())
            }
            if (response.isSuccessful) {
                Resource.Success(response.body()!!.toDomain())
            } else {
                Resource.Error("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error desconocido")
        }
    }
}
