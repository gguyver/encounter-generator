package com.example.encounter_generator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_encounter_parameters.view.*

class DisplayListAdapter (private val data : List<String>) : RecyclerView.Adapter<DisplayListAdapter.MyViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindText(data[position])
    }

    override fun getItemCount() = data.size

    class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        private var txtName = view.findViewById<TextView>(R.id.monster_name_view)
        fun bindText(text : String) {
            txtName.text = text
        }
    }
}