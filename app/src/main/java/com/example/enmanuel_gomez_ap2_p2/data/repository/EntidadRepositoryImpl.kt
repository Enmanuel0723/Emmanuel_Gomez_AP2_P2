package com.example.enmanuel_gomez_ap2_p2.data.repository

import com.example.enmanuel_gomez_ap2_p2.data.remote.api.EntidadApi
import com.example.enmanuel_gomez_ap2_p2.data.remote.dto.EntidadDto
import com.example.enmanuel_gomez_ap2_p2.domain.repository.EntidadRepository
import com.example.enmanuel_gomez_ap2_p2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EntidadRepositoryImpl @Inject constructor(
    private val api: EntidadApi
) : EntidadRepository {

    override fun getEntidades(): Flow<Resource<List<EntidadDto>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getEntidades()
            if (response.isSuccessful) {
                emit(Resource.Success(response.body() ?: emptyList()))
            } else {
                emit(Resource.Error("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error desconocido"))
        }
    }

    override suspend fun getEntidadById(id: Int): Resource<EntidadDto> {
        return try {
            val response = api.getEntidadById(id)
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error desconocido")
        }
    }

    override suspend fun saveEntidad(entidad: EntidadDto): Resource<EntidadDto> {
        return try {
            val response = if (entidad.entidadId == 0) {
                api.saveEntidad(entidad)
            } else {
                api.updateEntidad(entidad.entidadId, entidad)
            }
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error desconocido")
        }
    }

    override suspend fun deleteEntidad(entidad: EntidadDto): Resource<Unit> {
        return try {
            val response = api.deleteEntidad(entidad.entidadId)
            if (response.isSuccessful) {
                Resource.Success(Unit)
            } else {
                Resource.Error("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error desconocido")
        }
    }
}
