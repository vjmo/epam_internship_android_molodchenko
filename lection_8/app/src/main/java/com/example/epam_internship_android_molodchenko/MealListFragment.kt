package com.example.epam_internship_android_molodchenko

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.adapters.CategoryAdapter
import com.example.epam_internship_android_molodchenko.adapters.MealAdapter
import com.example.epam_internship_android_molodchenko.api.MealApi
import com.example.epam_internship_android_molodchenko.models.ModelCategory
import com.example.epam_internship_android_molodchenko.models.ModelCategoryList
import com.example.epam_internship_android_molodchenko.models.ModelMeal
import com.example.epam_internship_android_molodchenko.models.ModelMealList
import com.example.epam_internship_android_molodchenko.repository.MealsRepository
import com.example.epam_internship_android_molodchenko.uimodel.MealUIModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meal_list, container, false)
    }

    private val clickListenerMeal = object : OnItemClickListenerMeal {
        override fun onItemClick(meal: ModelMeal) {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.host_fragment, MealDetailsFragment.newInstance(meal.idMeal)
                )
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callCategories()
        callMeals()
    }

    lateinit var mealApi: MealApi

    private fun callCategories() {
        val recyclerViewCategory = view?.findViewById<RecyclerView>(R.id.rv_category)
        recyclerViewCategory?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        mealApi.getCategories().enqueue(object : Callback<ModelCategoryList> {
            override fun onResponse(
                call: Call<ModelCategoryList>,
                response: Response<ModelCategoryList>
            ) {
                val categories = response.body()!!.categories
                recyclerViewCategory?.adapter =
                    CategoryAdapter(categories, R.layout.category_item_list)
            }

            override fun onFailure(call: Call<ModelCategoryList>, t: Throwable) {
                Log.e("Callback Category", "Error")
            }
        })
    }

    private fun callMeals(category: ModelCategory) {

        val recyclerViewMeal = view?.findViewById<RecyclerView>(R.id.rv_one)
        recyclerViewMeal?.layoutManager = LinearLayoutManager(context)

        val mealsRepository: MealsRepository
        mealsRepository.loadMealsData(
            strCategory = category.nameCategory,
            onSuccess = { modelMealList ->
                recyclerViewMeal?.adapter = MealAdapter(, R.layout.item_list) }
        )
        mealApi.getMeals(/*не удается вставить )*/).enqueue(object : Callback<ModelMealList> {
            override fun onResponse(
                call: Call<ModelMealList>,
                response: Response<ModelMealList>
            ) {
                val meals = response.body()!!.meals
                recyclerViewMeal?.adapter =
                    MealAdapter(meals, R.layout.item_list)
            }

            override fun onFailure(call: Call<ModelMealList>, t: Throwable) {
                Log.e("Callback Meal", "Error")
            }
        })
    }

    companion object {
        fun newInstance(): MealListFragment = MealListFragment()
    }

}
