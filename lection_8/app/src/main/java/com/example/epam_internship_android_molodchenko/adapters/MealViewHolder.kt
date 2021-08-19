package com.example.epam_internship_android_molodchenko.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epam_internship_android_molodchenko.OnItemClickListenerCategory
import com.example.epam_internship_android_molodchenko.OnItemClickListenerMeal
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.models.ModelMeal

class MealViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    internal val title: TextView = view.findViewById(R.id.txt_itm_meal_one)
    internal val image: ImageView = view.findViewById(R.id.meal_one_img)

    fun bind(meal: ModelMeal, clickListener: OnItemClickListenerMeal) {
        title.text = meal.strMeal
        Glide.with(itemView.context).load(meal.strMealThumb).into(image)
        itemView.setOnClickListener {
            clickListener.onItemClick(meal)
        }
    }
}