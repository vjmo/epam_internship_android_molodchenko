package com.example.epam_internship_android_molodchenko

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.epam_internship_android_molodchenko.models.ModelCategory

class CategoryViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    internal var cardCategory = view.findViewById<CardView>(R.id.cardCategory)
    private val imageCategory = view.findViewById<ImageView>(R.id.imgCategory)

    fun bind(category: Category, clickListener: OnItemClickListenerCategory) {
        Glide.with(itemView.context).load(category.imgCategory).into(imageCategory)
        itemView.setOnClickListener {
            cardCategory.isSelected = !cardCategory.isSelected
            if (category.selectCategory)
                cardCategory.setBackgroundResource(R.color.pink)
            else cardCategory.setBackgroundResource(R.color.white)
            clickListener.onItemClick(category)
        }
    }


}