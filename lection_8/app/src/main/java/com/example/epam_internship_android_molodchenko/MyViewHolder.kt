package com.example.epam_internship_android_molodchenko

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(view: View, private val clickListener: OnItemClickListener?) :
    RecyclerView.ViewHolder(view) {
    private val title = view.findViewById<TextView>(R.id.txt_itm_meal_one)
    private val image = view.findViewById<ImageView>(R.id.meal_one_img)

    fun bind(model: Model){
        title.text = model.title

        //add img

        itemView.setOnClickListener { clickListener?.OnClick(model) }
    }
}