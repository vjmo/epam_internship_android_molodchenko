package com.example.epam_internship_android_molodchenko

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(val context: Context, val string: Array<String>, val images: Array<Int>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    val str: Array<String> = string
    val img: Array<Int> = images
    val ct: Context = context


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 0
    }
}