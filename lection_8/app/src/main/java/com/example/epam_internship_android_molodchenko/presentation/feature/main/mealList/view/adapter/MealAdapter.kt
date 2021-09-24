package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.models.ModelMeal
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener.OnItemClickListenerMeal

class MealAdapter() : RecyclerView.Adapter<MealViewHolder>() {

    private val mealItemList: MutableList<ModelMeal> = mutableListOf()

    var clickListener: OnItemClickListenerMeal = object : OnItemClickListenerMeal {
        override fun onItemClick(meal: ModelMeal) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(mealItemList[position], clickListener)
        holder.title.text = mealItemList[position].strMeal
    }

    override fun getItemCount(): Int = mealItemList.size

    fun setList(mealItemList: List<ModelMeal>) {
        this.mealItemList.clear()
        this.mealItemList.addAll(mealItemList)
        notifyDataSetChanged()
    }


    fun sortedByAscOrDesc(active: Boolean) {
        if (!active) {
            val newList = mealItemList.sortedBy { it.strMeal }.reversed()
            setList(newList)
        } else {
            val newList = mealItemList.sortedBy { it.strMeal }
            setList(newList)
        }

    }
}