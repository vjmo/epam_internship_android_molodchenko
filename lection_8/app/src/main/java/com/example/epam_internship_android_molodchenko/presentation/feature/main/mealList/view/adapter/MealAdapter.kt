package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener.OnItemClickListenerMeal
import com.example.epam_internship_android_molodchenko.presentation.model.MealUIModel

class MealAdapter() : RecyclerView.Adapter<MealViewHolder>() {

    private val mealItemListUI: MutableList<MealUIModel> = mutableListOf()

    var clickListener: OnItemClickListenerMeal? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        clickListener?.let { holder.bind(mealItemListUI[position], it) }
        holder.title.text = mealItemListUI[position].title
    }

    override fun getItemCount(): Int = mealItemListUI.size

    fun setList(list: List<MealUIModel>) {
        this.mealItemListUI.clear()
        this.mealItemListUI.addAll(list)
        notifyDataSetChanged()
    }


    fun sortedByAscOrDesc(active: Boolean) {
        if (!active) {
            val newList = mealItemListUI.sortedBy { it.title }.reversed()
            setList(newList)
        } else {
            val newList = mealItemListUI.sortedBy { it.title }
            setList(newList)
        }

    }
}