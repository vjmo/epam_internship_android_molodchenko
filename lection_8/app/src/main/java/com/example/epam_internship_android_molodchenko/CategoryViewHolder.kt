package com.example.epam_internship_android_molodchenko

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.models.ModelCategory

class CategoryViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    var cardCategory = view.findViewById<CardView>(R.id.cardCategory)
    val imageCategory = view.findViewById<ImageView>(R.id.imgCategory)

    fun bind(modelCategory: ModelCategory, clickListener: OnItemClickListenerCategory) {
        imageCategory.setImageResource(modelCategory.imgCategory)
        itemView.setOnClickListener {
            cardCategory.isSelected = !cardCategory.isSelected
            if (modelCategory.selectCategory)//?
                cardCategory.setBackgroundResource(R.color.pink)
            else cardCategory.setBackgroundResource(R.color.white)
            //clickListener.invoke(modelCategory)
            clickListener.onItemClick(modelCategory)
        }
    }


}