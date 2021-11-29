package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.adapter

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener.OnItemClickListenerCategory
import com.example.epam_internship_android_molodchenko.presentation.model.CategoryUIModel

class CategoryViewHolder(
    view: View,
    private val OnItemClickListenerCategory: OnItemClickListenerCategory
) :
    RecyclerView.ViewHolder(view) {

    private val cardCategory: CardView = view.findViewById(R.id.cardCategory)
    private val imageCategory: ImageView = view.findViewById(R.id.imgCategory)

    fun bind(categoryUI: CategoryUIModel) {

        Glide.with(itemView.context).load(categoryUI.imgCategory).into(imageCategory)
        setBackgroundColorItemView(categoryUI.activeCategory)
        itemView.setOnClickListener {
            OnItemClickListenerCategory.onItemClick(categoryUI.title)
        }
    }

    private fun setBackgroundColorItemView(active: Boolean) {
        if (active)
            cardCategory.setBackgroundColor(Color.parseColor("#FF7070"))
        else
            cardCategory.setBackgroundColor(Color.parseColor("#F5F2FC"))
    }
}

