package com.example.examen2evajavierlopez.modelos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="ObjetoLocal")
data class ObjetoLocal(

    @PrimaryKey(autoGenerate = true)
    val id:Int =0,
    val aux:String =""
)