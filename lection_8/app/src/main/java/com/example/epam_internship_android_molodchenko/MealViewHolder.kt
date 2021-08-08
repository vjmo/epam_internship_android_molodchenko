package com.example.epam_internship_android_molodchenko

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epam_internship_android_molodchenko.uimodel.MealUIModel

class MealViewHolder(view: View, private val clickListener: OnItemClickListenerMeal) :
    RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.txt_itm_meal_one)
    private val image = view.findViewById<ImageView>(R.id.meal_one_img)

    fun bind(mealUIModel: MealUIModel) {
        title.text = mealUIModel.title
        //image.setImageResource(mealUIModel.imgMeal)
        Glide.with(itemView.context).load(mealUIModel.imgMeal).into(image)
        itemView.setOnClickListener {
            clickListener.onItemClick(mealUIModel)
        }
    }
}