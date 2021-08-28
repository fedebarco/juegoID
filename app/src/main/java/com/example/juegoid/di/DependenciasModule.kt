package com.example.juegoid.di

import android.content.Context
import androidx.room.Room
import com.example.juegoid.rooma.Usuarios
import com.example.juegoid.rooma.UsuariosDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DependenciasModule {
    var codigo="ABCD"


    fun cambiacodigo(c:String){
        codigo=c
    }


    @Provides
    fun provideUsuario(@ApplicationContext context: Context):UsuariosDb{
        val room=Room.databaseBuilder(context,UsuariosDb::class.java,"Usuarios").build()
        return room
    }


    @Singleton
    @Provides
    fun provideCodigo(@ApplicationContext context: Context):String{
        val base=context.getSharedPreferences("midb",0)
        return base.getString("codigo","ABCD")!!
    }
}