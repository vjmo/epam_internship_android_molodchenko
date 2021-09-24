package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.adapter

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epam_internship_android_molodchenko.R

import com.example.epam_internship_android_molodchenko.data.database.model.ModelCategory

class CategoryViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    internal var cardCategory: CardView = view.findViewById(R.id.cardCategory)
    internal val imageCategory: ImageView = view.findViewById(R.id.imgCategory)

    fun bind(category: ModelCategory) {
        Glide.with(itemView.context).load(category.strCategoryThumb).into(imageCategory)
    }
}