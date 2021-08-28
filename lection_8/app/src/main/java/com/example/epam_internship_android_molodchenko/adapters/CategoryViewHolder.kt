package com.example.epam_internship_android_molodchenko.adapters

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epam_internship_android_molodchenko.OnItemClickListenerCategory
import com.example.epam_internship_android_molodchenko.R

import com.example.epam_internship_android_molodchenko.models.ModelCategory

class CategoryViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    internal var cardCategory: CardView = view.findViewById(R.id.cardCategory)
    internal val imageCategory: ImageView = view.findViewById(R.id.imgCategory)

    fun bind(category: ModelCategory) {
        Glide.with(itemView.context).load(category.strCategoryThumb).into(imageCategory)
    }
}