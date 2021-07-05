package com.example.epam_internship_android_molodchenko

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CategoryViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    val cardCategory = view.findViewById<CardView>(R.id.cardCategory)
    val imageCategory = view.findViewById<ImageView>(R.id.imgCategory)

    fun bind(modelCategory: ModelCategory, clickListener: OnItemClickListenerCategory) {
        imageCategory.setImageResource(modelCategory.imgCategory)
        itemView.setOnClickListener {
            cardCategory.isSelected = !cardCategory.isSelected
            if (modelCategory.selectCategory)
                cardCategory.setBackgroundResource(R.color.pink)
            else cardCategory.setBackgroundResource(R.color.white)
            clickListener.onItemClick(modelCategory)
        }
    }


}