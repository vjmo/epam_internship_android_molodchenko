package com.example.epam_internship_android_molodchenko.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.OnItemClickListenerCategory
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.models.ModelCategory

class CategoryAdapter(
    private val categoryItemList: MutableList<ModelCategory>,
    private val rowLayout: Int
) : RecyclerView.Adapter<CategoryViewHolder>() {

    var selectPosition = -1

    private val clickListener: OnItemClickListenerCategory = object : OnItemClickListenerCategory {
        override fun onItemClick(category: ModelCategory) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item_list, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryItemList[position], clickListener)

        selectPosition = holder.adapterPosition
        holder.cardCategory.isSelected = !holder.cardCategory.isSelected
        holder.cardCategory.setOnClickListener {
            if (selectPosition == position) holder.cardCategory.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.pink
                )
            ) else {
                holder.cardCategory.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.btn_meat_casserole
                    )
                )
            }
            notifyItemChanged(position)
        }

    }

    override fun getItemCount(): Int = categoryItemList.size

    fun setList(list: MutableList<ModelCategory>) {
        this.categoryItemList.addAll(list)
        notifyDataSetChanged()
    }

}