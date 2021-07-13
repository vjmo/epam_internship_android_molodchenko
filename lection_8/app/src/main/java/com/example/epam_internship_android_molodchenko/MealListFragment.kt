package com.example.epam_internship_android_molodchenko

import android.os.Build.ID
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.repository.CategoryRepository
import com.example.epam_internship_android_molodchenko.repository.MealsRepository

class MealListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clickListenerMeal = object : OnItemClickListenerMeal {
            override fun onItemClick(meal: Meal) {
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.host_fragment, MealDetailsFragment.newInstance(meal.id)
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
        fun mealsRV(){
            val recyclerViewMeal = view.findViewById<RecyclerView>(R.id.rv_one)
            val recyclerViewAdapterMeal = MealAdapter(clickListenerMeal)

            recyclerViewMeal.layoutManager = LinearLayoutManager(context)
            recyclerViewMeal.adapter = recyclerViewAdapterMeal

            recyclerViewAdapterMeal.clickListener
            recyclerViewAdapterMeal.setList(listOf())

            recyclerViewAdapterMeal.clickListener = clickListenerMeal
        }

        fun categoriesRV(){
            val recyclerViewCategory = view.findViewById<RecyclerView>(R.id.rv_category)
            val recyclerViewAdapterCategory = CategoryAdapter()

            recyclerViewCategory.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewCategory.adapter = recyclerViewAdapterCategory

            recyclerViewAdapterCategory.clickListener
            recyclerViewAdapterCategory.setList(
                listOf()
            )
        }
        fun categoryNet(){
            val categoryRepository = CategoryRepository()
            categoryRepository.initCategoriesData()
        }

        fun mealNet(){
            val mealRepository = MealsRepository()
            val mealAdapter = MealAdapter(clickListenerMeal)
        }


    }

    companion object {
        fun newInstance(): MealListFragment = MealListFragment()
    }

}