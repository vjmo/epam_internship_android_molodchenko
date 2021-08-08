package com.example.epam_internship_android_molodchenko

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.uimodel.MealUIModel

class MealAdapter(var clickListener: OnItemClickListenerMeal) : RecyclerView.Adapter<MealViewHolder>() {

    private val list: MutableList<MealUIModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MealViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(list[position])
        holder.title.text = list[position].title
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<MealUIModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}