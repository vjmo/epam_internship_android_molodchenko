package com.example.epam_internship_android_molodchenko

import android.content.Context
import android.content.SharedPreferences
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
import com.example.epam_internship_android_molodchenko.models.ModelMeal
import com.example.epam_internship_android_molodchenko.models.ModelMealList
import com.example.epam_internship_android_molodchenko.repository.CategoryRepositoryImpl
import com.example.epam_internship_android_molodchenko.repository.MealsRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealListFragment : Fragment() {

    private val sharedPreferences: SharedPreferences? by lazy {
        context?.getSharedPreferences(
            "settings_prefs",
            Context.MODE_PRIVATE
        )
    }

    private val compositeDisposable = CompositeDisposable()

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

    private fun callCategories() =
        compositeDisposable.add(
            categoryRepository.loadCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ categoryList ->
                    categoryAdapter.setList(categoryList.categories)
                    val lastIndexCategory = sharedPreferences?.getInt("id_category", 0) ?: 0
                    val loadLastIndexCategory = categoryList.categories[lastIndexCategory]
                    callMeals(loadLastIndexCategory)
                }, {
                    Log.e("Category", "Error")
                })
        )

    private fun callMeals(category: ModelCategory) =
        compositeDisposable.add(
            mealsRepository.loadMealsData(category.nameCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mealList ->
                    mealAdapter.setList(mealList.meals)
                    sharedPreferences?.edit()?.putInt("id_category", category.idCategory)?.apply()
                },
                    {
                        Log.e("Meal", "Error")
                    })
        )

    companion object {
        fun newInstance(): MealListFragment = MealListFragment()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
