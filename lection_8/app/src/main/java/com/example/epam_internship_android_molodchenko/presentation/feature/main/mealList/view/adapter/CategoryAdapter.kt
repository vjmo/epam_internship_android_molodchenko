package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.data.preferences.SharedPreferencesProvider
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener.OnItemClickListenerCategory
import com.example.epam_internship_android_molodchenko.presentation.model.CategoryUIModel

class CategoryAdapter() : RecyclerView.Adapter<CategoryViewHolder>() {


    private val categoryItemListUI: MutableList<CategoryUIModel> = mutableListOf()

    private var selectedItemPosition: Int = 0

    var clickListener: OnItemClickListenerCategory? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item_list, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryItemListUI[position])

        fun saveColorCurrentCard() {
            val preferences = SharedPreferencesProvider(
                holder.itemView.context.getSharedPreferences(
                    "MyPreferences",
                    Context.MODE_PRIVATE
                )
            )

                //preferences.saveStr(KEY_CATEGORY, holder.cardCategory.cardBackgroundColor.toString())
            //preferences.str(KEY_CATEGORY, holder.cardCategory.cardBackgroundColor.toString())
        }

        if (selectedItemPosition == holder.adapterPosition) {
            holder.cardCategory.setBackgroundColor(Color.parseColor("#FF7070"))
            saveColorCurrentCard()

        } else {
            holder.cardCategory.setBackgroundColor(Color.parseColor("#F5F2FC"))
            saveColorCurrentCard()
        }

        holder.itemView.setOnClickListener {
            selectedItemPosition = holder.adapterPosition
            clickListener?.onItemClick(categoryItemListUI[position])
            saveColorCurrentCard()
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int = categoryItemListUI.size


    fun setList(list: List<CategoryUIModel>) {
        this.categoryItemListUI.clear()
        this.categoryItemListUI.addAll(list)
        notifyDataSetChanged()
    }

    companion object {
        const val KEY_CATEGORY = "idCategory"
    }

}