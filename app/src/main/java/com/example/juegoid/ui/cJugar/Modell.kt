package com.example.juegoid.ui.cJugar

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juegoid.model.Questions
import com.example.juegoid.rooma.Usuarios
import com.example.juegoid.rooma.UsuariosDb
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class Modell @ViewModelInject constructor(val getRoom:UsuariosDb):ViewModel() {
    val iniciaColores=MutableLiveData<Int>(0)
    val colorVa=MutableLiveData<Int>(0)
    val codigoJuego=MutableLiveData<String>("ABCD")
    val usuarios= Usuarios(0,"null","ABCD",false,150)
    val pUsu= MutableLiveData<Usuarios>()
    val sUsu=MutableLiveData<MutableList<Usuarios>>()
    val questions=MutableLiveData<MutableList<Questions>>(null)
    private var database: DatabaseReference=Firebase.database.reference
    var acaba=MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            val usuarios=getRoom.usuariosDao().getAll()
            //usuariosen.postValue(getRoom.usuariosDao().getAll())
            var uPrin=usuarios[0]
            pUsu.postValue(uPrin)
            codigoJuego.postValue(uPrin.c)
            desordenaEnvia(uPrin.p,uPrin.c)

            database.child("juego").child(uPrin.c).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val uUti3= mutableListOf<Usuarios>()
                    val nusu3 = mutableListOf<Int>()
                    val pusu3= mutableListOf<String>()
                    for (postSnapshot in dataSnapshot.children) {
                        pusu3.add(postSnapshot.key.toString())
                        nusu3.add(postSnapshot.getValue<Int>()!!)
                        val uuutd= Usuarios(0,postSnapshot.key.toString(),uPrin.c,uPrin.p,postSnapshot.getValue<Int>()!!)
                        uUti3.add(uuutd)
                    }
                    sUsu.postValue(uUti3)
                    //model.users.postValue(nusu3)
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
            database.child("preguntas").child(uPrin.c).addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val questionList= mutableListOf<Questions>()

                    for (postSnapshot in snapshot.children) {
                        val id=postSnapshot.child("id").getValue<String>()!!
                        val pregun=postSnapshot.child("pregun").getValue<String>()!!
                        val resul=postSnapshot.child("resul").getValue<String>()!!
                        val quien=postSnapshot.child("quien").getValue<String>()!!
                        val quienr=postSnapshot.child("quienresponde").getValue<String>()!!
                        val question=Questions(id,pregun,resul,quien,quienr)
                        questionList.add(question)
                    }
                    questions.postValue(questionList)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        }
    }





    fun desordenaEnvia(esCentral:Boolean,codigo:String){
        val aleatorios= mutableListOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)
        if (esCentral){
            val des2=desordeAl(aleatorios)
            database.child("cadena").child(codigo).setValue(des2)
        }
    }

    fun desordenaPrimer():Int{
        val unoaOcho= mutableListOf(0,1,2,3,4,5,6,7)
        return unoaOcho.random()
    }

    fun desordeAl(text:MutableList<Int>): MutableList<Int> {
        val te2= mutableListOf<Int>()
        for (i in 1..16){
            val r=text.random()
            te2.add(r)
            text.remove(r)
        }
        return te2
    }

    fun actualizadinero(dineros:Int){
        val usuarioPrincipal=pUsu.value!!
        usuarioPrincipal.dinero=dineros
        database.child("juego").child(usuarioPrincipal.codigo).child(usuarioPrincipal.noUsuario).setValue(dineros)
    }

    fun respuestaTodos(idPregunta:String,respuesta:String){
        database.child("preguntas").child(codigoJuego.value!!).child(idPregunta).child("quie").setValue(pUsu.value!!.noUsuario)
        database.child("preguntas").child(codigoJuego.value!!).child(idPregunta).child("resul").setValue(respuesta)
        val dinero3=pUsu.value!!.dinero-12
        actualizadinero(dinero3)
    }

    fun respuestaUno(idPregunta:String,respuesta:String){
        database.child("preguntas").child(idPregunta).child("quie").setValue(pUsu.value!!.noUsuario)
        database.child("preguntas").child(idPregunta).child("resul").setValue(respuesta)
        val dinero3=pUsu.value!!.dinero-4
        actualizadinero(dinero3)
    }

    fun enviaPregunta(pregunta:String){
        val newData=database.child("preguntas").child(codigoJuego.value!!).push()             ///codigoJuego.value!!).push()
        val questions= Questions(newData.key!!,pregunta,"",pUsu.value!!.noUsuario,"sin respuesta")
        if (pregunta!=""){
            newData.setValue(questions)
        }
    }




}



