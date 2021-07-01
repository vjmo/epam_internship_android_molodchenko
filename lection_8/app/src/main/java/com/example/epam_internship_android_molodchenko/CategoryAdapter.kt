package com.example.epam_internship_android_molodchenko

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter : RecyclerView.Adapter<CategoryViewHolder>() {

    private val list: MutableList<ModelCategory> = mutableListOf()
    val clickListener: (ModelCategory) -> Unit = {}


   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item_list, parent, false)
        return CategoryViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<ModelCategory>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

}