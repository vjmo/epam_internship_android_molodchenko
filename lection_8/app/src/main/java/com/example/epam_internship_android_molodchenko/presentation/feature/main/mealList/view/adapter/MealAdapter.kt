package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.data.model.meal.ModelMealDto
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener.OnItemClickListenerMeal

class MealAdapter() : RecyclerView.Adapter<MealViewHolder>() {

    private val mealDtoItemList: MutableList<ModelMealDto> = mutableListOf()

    var clickListener: OnItemClickListenerMeal = object : OnItemClickListenerMeal {
        override fun onItemClick(mealDto: ModelMealDto) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(mealDtoItemList[position], clickListener)
        holder.title.text = mealDtoItemList[position].strMeal
    }

    override fun getItemCount(): Int = mealDtoItemList.size

    fun setList(mealDtoItemList: List<ModelMealDto>) {
        this.mealDtoItemList.clear()
        this.mealDtoItemList.addAll(mealDtoItemList)
        notifyDataSetChanged()
    }


    fun sortedByAscOrDesc(active: Boolean) {
        if (!active) {
            val newList = mealDtoItemList.sortedBy { it.strMeal }.reversed()
            setList(newList)
        } else {
            val newList = mealDtoItemList.sortedBy { it.strMeal }
            setList(newList)
        }

    }
}