package com.example.android.newtabbedactivity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class RecyclerViewAdapter(private val MyDataSet : ArrayList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
       return MyDataSet.size
    }

    //replace the contents that is in the MyViewHolder
    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.txtV?.text=MyDataSet[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
     val textView=LayoutInflater.from(parent?.context).inflate(R.layout.my_text_view, parent, false) as TextView
        return MyViewHolder(textView)
    }

    class MyViewHolder(val txtV:TextView) : RecyclerView.ViewHolder(txtV)
}

