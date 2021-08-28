package com.example.juegoid.ui.cJugar


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.juegoid.R

class ColorAdapter(list1:MutableList<String>,list2:MutableList<Int>, context: Context): BaseAdapter() {
    internal var sList = list1
    internal var sList2=list2
    internal var sList3=list2
    private val mInflator: LayoutInflater = LayoutInflater.from(context)

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
        val vh: ListRowHolder2
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.listacolores, parent, false)
            vh = ListRowHolder2(view,sList,sList2,position)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder2
        }

        vh.label.text = sList[position]
        vh.labelnum.text=sList2[position].toString()
        vh.labelnum.text=sList3[position].toString()
        return view
    }

}
private class ListRowHolder2(row: View?, lasbaseD: MutableList<String>,losvalD:MutableList<Int>, position1: Int) {
    val label: TextView = row?.findViewById(R.id.textoColores) as TextView
    val labelnum: TextView =row?.findViewById(R.id.CompartidasColores) as TextView
    val botonagr: TextView =row?.findViewById(R.id.puntajeColores) as TextView


}