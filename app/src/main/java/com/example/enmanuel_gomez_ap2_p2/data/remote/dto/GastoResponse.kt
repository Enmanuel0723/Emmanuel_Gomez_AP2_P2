package com.example.enmanuel_gomez_ap2_p2.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GastoResponse(
    @Json(name = "gastoId") val gastoId: Int = 0,
    @Json(name = "fecha") val fecha: String = "",
    @Json(name = "suplidor") val suplidor: String = "",
    @Json(name = "ncf") val ncf: String = "",
    @Json(name = "itbis") val itbis: Double = 0.0,
    @Json(name = "monto") val monto: Double = 0.0
)
