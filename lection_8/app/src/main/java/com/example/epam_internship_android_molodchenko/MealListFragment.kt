package com.example.epam_internship_android_molodchenko

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MealListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meal_list, container, false)
    }

    val modelMealOne = ModelMeal(
        "Soy-Glazed Meatloaves with Wasabi Mashed Potatoes & Roasted Carrots",
        R.drawable.meal_one
    )
    val modelMealTwo = ModelMeal("Steak Diane", R.drawable.meal_two)

    val modelCategoryOne = ModelCategory(1, R.drawable.tb_one, false)
    val modelCategoryTwo = ModelCategory(2, R.drawable.tb_two, false)
    val modelCategoryThree = ModelCategory(3, R.drawable.tb_three, false)
    val modelCategoryFour = ModelCategory(4, R.drawable.tb_four, false)
    val modelCategoryFive = ModelCategory(5, R.drawable.tb_five, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//1
        var clickListenerMeal = object : OnItemClickListenerMeal {
            override fun onItemClick(modelMeal: ModelMeal)  {
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.host_fragment, MealDetailsFragment.newInstance(modelMeal.title)
                        )
                        .addToBackStack(null)
                        .commit()
                }
        }

        val recyclerViewMeal = view.findViewById<RecyclerView>(R.id.rv_one)
        val recyclerViewAdapterMeal = MealAdapter(clickListenerMeal)

        recyclerViewMeal.layoutManager = LinearLayoutManager(context)
        recyclerViewMeal.adapter = recyclerViewAdapterMeal

        recyclerViewAdapterMeal.clickListener
        recyclerViewAdapterMeal.setList(listOf(modelMealOne, modelMealTwo))

        recyclerViewAdapterMeal.clickListener = clickListenerMeal

        // 2

        val recyclerViewCategory = view.findViewById<RecyclerView>(R.id.rv_category)
        val recyclerViewAdapterCategory = CategoryAdapter()

        recyclerViewCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false )
        recyclerViewCategory.adapter = recyclerViewAdapterCategory

        recyclerViewAdapterCategory.clickListener
        recyclerViewAdapterCategory.setList(
            listOf(
                modelCategoryOne,
                modelCategoryTwo,
                modelCategoryThree,
                modelCategoryFour,
                modelCategoryFive
            )
        )
    }

   /* private fun setOnClickParentFrMan() {
        parentFragmentManager
            .beginTransaction()
            .replace(
                R.id.host_fragment,
                MealDetailsFragment.newInstance(modelMealOne.title),
                ""
            )
            .addToBackStack(null)
            .commit()
    }*/

    companion object {
        fun newInstance(): MealListFragment = MealListFragment()
    }

}