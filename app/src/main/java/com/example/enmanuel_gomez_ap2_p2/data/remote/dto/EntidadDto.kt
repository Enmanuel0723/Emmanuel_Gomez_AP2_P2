package com.example.enmanuel_gomez_ap2_p2.data.remote.dto

import com.google.gson.annotations.SerializedName

data class EntidadDto(
    @SerializedName("entidadId") val entidadId: Int = 0,
    @SerializedName("nombre") val nombre: String = ""
) {
    override fun toString() = nombre
}
