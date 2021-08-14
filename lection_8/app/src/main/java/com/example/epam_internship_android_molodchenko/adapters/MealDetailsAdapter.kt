package com.example.epam_internship_android_molodchenko.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.models.ModelMeal
import com.example.epam_internship_android_molodchenko.models.ModelMealDetails

class MealDetailsAdapter() : RecyclerView.Adapter<MealDetailsViewHolder>() {

    private val tagItemList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealDetailsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tag_details, parent, false)
        return MealDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealDetailsViewHolder, position: Int) {
        holder.bind(tagItemList[position])
    }

    override fun getItemCount(): Int = tagItemList.size

    fun setList(tagItemList: MutableList<String>) {
        this.tagItemList.clear()
        this.tagItemList.addAll(tagItemList)
        notifyDataSetChanged()
    }
}