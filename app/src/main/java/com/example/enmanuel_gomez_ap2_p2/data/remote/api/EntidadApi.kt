package com.example.enmanuel_gomez_ap2_p2.data.remote.api

import com.example.enmanuel_gomez_ap2_p2.data.remote.dto.EntidadDto
import retrofit2.Response
import retrofit2.http.*

interface EntidadApi {

    @GET("entidades")
    suspend fun getEntidades(): Response<List<EntidadDto>>

    @GET("entidades/{id}")
    suspend fun getEntidadById(@Path("id") id: Int): Response<EntidadDto>

    @POST("entidades")
    suspend fun saveEntidad(@Body entidad: EntidadDto): Response<EntidadDto>

    @PUT("entidades/{id}")
    suspend fun updateEntidad(@Path("id") id: Int, @Body entidad: EntidadDto): Response<EntidadDto>

    @DELETE("entidades/{id}")
    suspend fun deleteEntidad(@Path("id") id: Int): Response<Unit>
}
