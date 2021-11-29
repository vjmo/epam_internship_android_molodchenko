package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener.OnItemClickListenerCategory
import com.example.epam_internship_android_molodchenko.presentation.model.CategoryUIModel

class CategoryAdapter() :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private val categoryItemListUI: MutableList<CategoryUIModel> = mutableListOf()

    var clickListener: OnItemClickListenerCategory = object : OnItemClickListenerCategory {
        override fun onItemClick(strCategory: String) {
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item_list, parent, false)
        return CategoryViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryItemListUI[position])
    }

    override fun getItemCount(): Int = categoryItemListUI.size

    fun setList(listCategory: List<CategoryUIModel>) {
        this.categoryItemListUI.clear()
        this.categoryItemListUI.addAll(listCategory)
        notifyDataSetChanged()
    }

    fun selectItem(titleCategory: String) {
        setList(categoryItemListUI.map { categoryUIModel ->
            categoryUIModel.copy(activeCategory = titleCategory == categoryUIModel.title)
        })
    }

}