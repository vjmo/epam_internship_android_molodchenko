package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener.OnItemClickListenerMeal
import com.example.epam_internship_android_molodchenko.presentation.model.MealUIModel

class MealViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    internal val title: TextView = view.findViewById(R.id.txt_itm_meal_one)
    internal val image: ImageView = view.findViewById(R.id.meal_one_img)

    fun bind(mealUI: MealUIModel, clickListener: OnItemClickListenerMeal) {
        title.text = mealUI.title
        Glide.with(itemView.context).load(mealUI.title).into(image)
        itemView.setOnClickListener {
            clickListener.onItemClick(mealUI)
        }
    }
}