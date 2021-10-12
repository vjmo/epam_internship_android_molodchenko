package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener.OnItemClickListenerCategory
import com.example.epam_internship_android_molodchenko.presentation.model.CategoryUIModel

class CategoryAdapter() : RecyclerView.Adapter<CategoryViewHolder>() {

    val categoryItemListUI: MutableList<CategoryUIModel> = mutableListOf()

    var clickListener: OnItemClickListenerCategory? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item_list, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryItemListUI[position])

        val selectPosition: Int = holder.adapterPosition

        holder.cardCategory.isSelected = !holder.cardCategory.isSelected
        holder.cardCategory.setOnClickListener {
            if (selectPosition == position) {
                holder.cardCategory.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.pink
                    )
                )
            } else {
                holder.cardCategory.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.btn_meat_casserole
                    )
                )
            }

            notifyItemChanged(position)
            clickListener?.onItemClick(categoryItemListUI[position])
        }
    }

    override fun getItemCount(): Int = categoryItemListUI.size

    fun setList(list: List<CategoryUIModel>) {
        this.categoryItemListUI.clear()
        this.categoryItemListUI.addAll(list)
        notifyDataSetChanged()
    }

}