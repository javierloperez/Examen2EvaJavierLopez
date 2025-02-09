package com.example.examen2evajavierlopez.modelos

import androidx.room.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
class ObjetoJson(

    @SerialName(value = "id")
    val id:String = "",
    @SerialName(value = "aux")
    val aux:String
)