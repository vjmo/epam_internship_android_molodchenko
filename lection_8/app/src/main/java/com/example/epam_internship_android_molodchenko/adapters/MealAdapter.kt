package com.example.epam_internship_android_molodchenko.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.OnItemClickListenerMeal
import com.example.epam_internship_android_molodchenko.R
import com.example.epam_internship_android_molodchenko.models.ModelMeal
import com.example.epam_internship_android_molodchenko.uimodel.MealUIModel

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

    //sortedby...(1,2 notify set data
    fun sortedByAscOrDesc(active: Boolean) {


        if (!active) {
            val newMealItemList = mealItemList.toList().sortedBy { v1 -> v1.strMeal }//.sortBy { v1 -> v1.strMeal  }//( { v1, v2 -> v1.strMeal > v2.strMeal})

            setList(newMealItemList)
            notifyDataSetChanged()
        } else {
            val newMealItemList = mealItemList.toList().sortedBy { v1 -> v1.strMeal }//.sortBy { v1 -> v1.strMeal  }//( { v1, v2 -> v1.strMeal > v2.strMeal})

            setList(newMealItemList)
            notifyDataSetChanged()
        }

    }
}