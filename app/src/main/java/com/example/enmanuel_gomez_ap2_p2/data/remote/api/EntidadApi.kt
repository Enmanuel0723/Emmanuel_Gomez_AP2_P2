package com.example.enmanuel_gomez_ap2_p2.data.remote.api

import com.example.enmanuel_gomez_ap2_p2.data.remote.dto.GastoRequest
import com.example.enmanuel_gomez_ap2_p2.data.remote.dto.GastoResponse
import retrofit2.Response
import retrofit2.http.*

interface GastoApi {

    @GET("api/Gastos")
    suspend fun getGastos(): Response<List<GastoResponse>>

    @GET("api/Gastos/{id}")
    suspend fun getGastoById(@Path("id") id: Int): Response<GastoResponse>

    @POST("api/Gastos")
    suspend fun saveGasto(@Body gasto: GastoRequest): Response<GastoResponse>

    @PUT("api/Gastos/{id}")
    suspend fun updateGasto(@Path("id") id: Int, @Body gasto: GastoRequest): Response<GastoResponse>
}
