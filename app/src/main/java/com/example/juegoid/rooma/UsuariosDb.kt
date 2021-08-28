package com.example.juegoid.rooma

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Usuarios::class],
    version=1
)
abstract class UsuariosDb:RoomDatabase() {

    abstract fun usuariosDao():UsuariosDao
}