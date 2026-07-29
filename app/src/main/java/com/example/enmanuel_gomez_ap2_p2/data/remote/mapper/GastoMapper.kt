package com.example.enmanuel_gomez_ap2_p2.data.remote.mapper

import com.example.enmanuel_gomez_ap2_p2.data.remote.dto.GastoRequest
import com.example.enmanuel_gomez_ap2_p2.data.remote.dto.GastoResponse
import com.example.enmanuel_gomez_ap2_p2.domain.model.Gasto

fun GastoResponse.toDomain() = Gasto(
    gastoId = gastoId,
    fecha = fecha,
    suplidor = suplidor,
    ncf = ncf,
    itbis = itbis,
    monto = monto
)

fun Gasto.toRequest() = GastoRequest(
    fecha = fecha,
    suplidor = suplidor,
    ncf = ncf,
    itbis = itbis,
    monto = monto
)
