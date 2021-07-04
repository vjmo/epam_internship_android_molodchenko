package com.example.epam_internship_android_molodchenko

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MealAdapter(var clickListener: OnItemClickListenerMeal) : RecyclerView.Adapter<MealViewHolder>() {

    private val list: MutableList<ModelMeal> = mutableListOf()
    //var clickListener: (ModelMeal) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MealViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(list[position])
        holder.title.text = list[position].title
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<ModelMeal>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}