package com.example.epam_internship_android_molodchenko

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryViewHolder(view: View, private val clickListener: (ModelCategory) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val imageCategory = view.findViewById<ImageView>(R.id.beef_tb)

    fun bind(modelCategory: ModelCategory) {
        imageCategory.setBackgroundResource(modelCategory.imgCategory)

        itemView.setOnClickListener {
            if (modelCategory.selectCategory)
                imageCategory.setBackgroundResource(R.color.pink)
            else imageCategory.setBackgroundResource(R.color.white)
            clickListener.invoke(modelCategory)
        }
    }
}