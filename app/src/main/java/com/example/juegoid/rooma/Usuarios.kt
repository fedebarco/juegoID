package com.example.juegoid.rooma

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Usuarios(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val n:String,
    val c:String,
    val p:Boolean,
    val d:Int){

    var dinero=d
    var codigo=c
    var noUsuario=n
}