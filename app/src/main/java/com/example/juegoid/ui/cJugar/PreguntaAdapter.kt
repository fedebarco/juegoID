package com.example.juegoid.ui.cJugar

import android.content.Context
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.juegoid.R
import com.example.juegoid.model.Questions

class PreguntaAdapter(questions:MutableList<Questions>, model: Modell, context: Context): BaseAdapter() {
    private var sList0= mutableListOf<String>()
    private var sList = mutableListOf<String>()
    private var sList2= mutableListOf<String>()
    private var sList3= mutableListOf<String>()
    private var sList4= mutableListOf<String>()
    private val mInflator: LayoutInflater = LayoutInflater.from(context)
    var model1=model
    init {
       for(postquestion in questions){
           sList0.add(postquestion.quien)
           sList.add(postquestion.pregun)
           sList2.add(postquestion.resul)
           sList3.add(postquestion.id)
           sList4.add(postquestion.quienresponde)
       }
    }


    override fun getCount(): Int {
        return sList.size
    }

    override fun getItem(position: Int): Any {
        return sList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ListRowHolder3
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.listapreguntas, parent, false)
            vh = ListRowHolder3(view,sList3,position,model1)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder3
        }
        vh.quienP.text="Pregunta: "+sList0[position]
        vh.label.text = sList[position]
        vh.labelnum.text=sList2[position]
        vh.quienR.text="Responde:"+sList4
        //vh.labelnum.text=sList3[position].toString()
        return view
    }
}
private class ListRowHolder3(row: View?, lasbaseD: MutableList<String>, position1: Int,modell: Modell) {
    val quienP: TextView = row?.findViewById(R.id.quienpregunta) as TextView
    val quienR: TextView = row?.findViewById(R.id.quienresponde) as TextView
    val label: TextView = row?.findViewById(R.id.preguntaPregunta) as TextView
    val labelnum: TextView =row?.findViewById(R.id.respuestaPregunta) as EditText
    val bTodos:ImageButton=row?.findViewById(R.id.botodos) as ImageButton
    val bAel:ImageButton=row?.findViewById(R.id.aelPre) as ImageButton
    //val botonagr: TextView =row?.findViewById(R.id.) as TextView
    init {
        bTodos.setOnClickListener{
            modell.respuestaTodos(lasbaseD[position1],labelnum.text.toString())
            //labelnum.isEnabled=false
            //labelnum.keyListener=null
            labelnum.inputType=InputType.TYPE_NULL
            label.isActivated=false
            labelnum.isFocusable=false
            labelnum.isEnabled=false
            labelnum.keyListener=null
            labelnum.isFocusableInTouchMode=false
        }
        bAel.setOnClickListener {
            modell.respuestaUno(lasbaseD[position1],labelnum.text.toString())
        }
    }
}