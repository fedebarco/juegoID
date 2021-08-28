package com.example.juegoid.ui.csalaespera

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juegoid.rooma.Usuarios
import com.example.juegoid.rooma.UsuariosDb
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class VMSalaE @ViewModelInject constructor(val getRoom: UsuariosDb):ViewModel() {
    val codigoJuego= MutableLiveData<String>("ABCD")
    val nombreU=MutableLiveData<String>("ABCD")
    private var database: DatabaseReference = Firebase.database.reference
    val usuariosen=MutableLiveData<MutableList<Usuarios>>()
    val nombresus= MutableLiveData<MutableList<String>>(mutableListOf())
    val iniciaOtraA=MutableLiveData<Boolean>(false)
    val principal=MutableLiveData<Boolean>(false)

    init {
        viewModelScope.launch {
            val usuarios=getRoom.usuariosDao().getAll()
            usuariosen.postValue(getRoom.usuariosDao().getAll())
            var uPrin=usuarios[0]
            var codigo=uPrin.c
            nombreU.postValue(uPrin.n)
            codigoJuego.postValue(codigo)
            database.child("juego").child(codigo).child(uPrin.n).setValue(80)
            database.child("juego").child(codigo).addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    val magui = dataSnapshot.key.toString()
                    nombresus.value!!.add(magui)
                    nombresus.postValue(nombresus.value)
                    //    var cola=dataSnapshot.child("ABCD").getValue<Map<String, String>>()

                }

                override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    //Log.d(TAG, "onChildChanged: ${dataSnapshot.key}")

                    // A comment has changed, use the key to determine if we are displaying this
                    // comment and if so displayed the changed comment.
                    //val newComment = dataSnapshot.getValue<Comment>()
                    //val commentKey = dataSnapshot.key

                    // ...
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                    //Log.d(TAG, "onChildRemoved:" + dataSnapshot.key!!)

                    // A comment has changed, use the key to determine if we are displaying this
                    // comment and if so remove it.
                    //val commentKey = dataSnapshot.key

                    // ...
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    //Log.d(TAG, "onChildMoved:" + dataSnapshot.key!!)

                    // A comment has changed position, use the key to determine if we are
                    // displaying this comment and if so move it.
                    //val movedComment = dataSnapshot.getValue<Comment>()
                    //val commentKey = dataSnapshot.key

                    // ...
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
            database.child("juegos").child(codigo+"n").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Get Post object and use the values to update the UI
                    val post = dataSnapshot.getValue<String>()
                    if(post=="inicia"){
                        iniciaOtraA.postValue(true)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    //Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                }
            })

        }

    }


    fun obtenernombres():MutableList<String>{
        var nombress= mutableListOf<String>()
        var userss=usuariosen.value!!
        for(i in 0..userss.size-1){
            nombress.add(userss[i].noUsuario)
        }
        return nombress
    }


    fun comandoInicia(cond:Boolean){
        if (cond){
            database.child("juegos").child(codigoJuego.value!!+"n").setValue("inicia")
        }
    }


}