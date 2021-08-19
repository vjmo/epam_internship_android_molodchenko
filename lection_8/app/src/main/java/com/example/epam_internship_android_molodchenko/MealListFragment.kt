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
import com.example.epam_internship_android_molodchenko.models.ModelCategory
import com.example.epam_internship_android_molodchenko.models.ModelCategoryList
import com.example.epam_internship_android_molodchenko.models.ModelMeal
import com.example.epam_internship_android_molodchenko.models.ModelMealList
import com.example.epam_internship_android_molodchenko.repository.CategoryRepositoryImpl
import com.example.epam_internship_android_molodchenko.repository.MealsRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealListFragment : Fragment() {

    private val categoryRepository by lazy { CategoryRepositoryImpl(RetrofitInstance.mealApi) }
    private val mealsRepository by lazy { MealsRepositoryImpl(RetrofitInstance.mealApi) }

    private val mealAdapter = MealAdapter()
    private val categoryAdapter = CategoryAdapter()

    private val recyclerViewCategory by lazy { view?.findViewById<RecyclerView>(R.id.rv_category) }
    private val recyclerViewMeal by lazy { view?.findViewById<RecyclerView>(R.id.rv_one) }

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        callCategories()
    }

    private fun initView() {
        recyclerViewMeal?.adapter = mealAdapter
        recyclerViewCategory?.adapter = categoryAdapter
        recyclerViewMeal?.layoutManager = LinearLayoutManager(context)
        recyclerViewCategory?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter.clickListener = object : OnItemClickListenerCategory {
            override fun onItemClick(category: ModelCategory) {
                callMeals(category)
            }
        }
        mealAdapter.clickListener = clickListenerMeal
    }

    private fun callCategories() {

        categoryRepository.loadCategories().enqueue(object : Callback<ModelCategoryList> {
            override fun onResponse(
                call: Call<ModelCategoryList>,
                response: Response<ModelCategoryList>
            ) {
                response.body()?.categories?.let {
                    categoryAdapter.setList(it)
                }
            }

            override fun onFailure(call: Call<ModelCategoryList>, t: Throwable) {
                Log.e("Callback Category", "Error")
            }
        })
    }

    private fun callMeals(category: ModelCategory) {

        mealsRepository.loadMealsData(strCategory = category.nameCategory)
            .enqueue(object : Callback<ModelMealList> {
                override fun onResponse(
                    call: Call<ModelMealList>,
                    response: Response<ModelMealList>
                ) {
                    response.body()?.meals?.let {
                        mealAdapter.setList(it)
                    }
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
