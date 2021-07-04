package com.example.epam_internship_android_molodchenko

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MealViewHolder(view: View, private val clickListener: OnItemClickListenerMeal) :
    RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById<TextView>(R.id.txt_itm_meal_one)
    val image = view.findViewById<ImageView>(R.id.meal_one_img)

    fun bind(modelMeal: ModelMeal){
        title.text = modelMeal.title
        image.setImageResource(modelMeal.imgRes)
        itemView.setOnClickListener {
            clickListener.onItemClick(modelMeal)
        }
    }
}