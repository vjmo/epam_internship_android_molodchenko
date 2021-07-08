package com.example.epam_internship_android_molodchenko

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epam_internship_android_molodchenko.models.ModelMeal

class MealViewHolder(view: View, private val clickListener: OnItemClickListenerMeal) :
    RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.txt_itm_meal_one)
    private val image = view.findViewById<ImageView>(R.id.meal_one_img)

    fun bind(meal: Meal) {
        title.text = meal.title
        //image.setImageResource(meal.imgMeal)
        Glide.with(itemView.context).load(meal.imgMeal).into(image)
        itemView.setOnClickListener {
            clickListener.onItemClick(meal)
        }
    }
}