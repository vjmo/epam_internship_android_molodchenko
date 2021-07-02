package com.example.epam_internship_android_molodchenko

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter : RecyclerView.Adapter<CategoryViewHolder>() {

    private val list: MutableList<ModelCategory> = mutableListOf()
    private var selectPosition = -1
    val clickListener: OnItemClickListenerCategory = object : OnItemClickListenerCategory {
        override fun onItemClick(modelCategory: ModelCategory) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item_list, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position], clickListener)

        selectPosition = holder.adapterPosition
        holder.cardCategory.isSelected = !holder.cardCategory.isSelected
        if (selectPosition == position) holder.cardCategory.setCardBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                R.color.white
            )
        ) else {
            holder.cardCategory.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.pink
                )
            )
        }
        clickListener.apply { notifyDataSetChanged() }
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<ModelCategory>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

}