package com.example.juegoid.rooma

import androidx.room.*

@Dao
interface UsuariosDao {

    @Query("SELECT * FROM Usuarios")
    suspend fun getAll():MutableList<Usuarios>

    @Query("SELECT * FROM Usuarios WHERE id=:id" )
    suspend fun getByID(id:Int):Usuarios

    @Update
    suspend fun update(person:Usuarios)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserte(people:MutableList<Usuarios>)

    @Delete
    suspend fun Delete(person: Usuarios)
}