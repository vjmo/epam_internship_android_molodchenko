package com.example.epam_internship_android_molodchenko

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.epam_internship_android_molodchenko.adapters.CategoryAdapter
import com.example.epam_internship_android_molodchenko.adapters.MealAdapter
import com.example.epam_internship_android_molodchenko.models.ModelCategory
import com.example.epam_internship_android_molodchenko.models.ModelMeal
import com.example.epam_internship_android_molodchenko.repository.CategoryRepositoryImpl
import com.example.epam_internship_android_molodchenko.repository.MealsRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class MealListFragment : Fragment() {

    private val sharedPreferences: SharedPreferences? by lazy {
        context?.getSharedPreferences(
            "settings_prefs",
            Context.MODE_PRIVATE
        )
    }

    private val compositeDisposable = CompositeDisposable()

    private val categoryRepository by lazy {
        CategoryRepositoryImpl(
            RetrofitInstance.mealApi,
            TestApp.INSTANCE.db
        )
    }
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

        compositeDisposable.add(
            categoryRepository.requestCategories()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.d("MealListFragment", "Success")
                }, {
                    it.printStackTrace()
                })
        )

        compositeDisposable.add(
            categoryRepository.observeCategory()
                .subscribeOn(Schedulers.io())
                .flatMap { list ->
                    val lastIndexCategory = sharedPreferences?.getInt("id_category", 0) ?: 0
                    val category = list[lastIndexCategory]
                    return@flatMap mealsRepository.loadMealsData(category.nameCategory)
                        .doOnSuccess {
                            sharedPreferences?.edit()
                                ?.putInt("id_category", category.idCategory)
                                ?.apply()
                        }
                        .map { Pair(list, it) }
                        .toFlowable()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    showCategories(it.first)
                    mealAdapter.setList(it.second.meals)
                }, {
                    it.printStackTrace()
                })
        )

    }

    private fun initView() {
        recyclerViewMeal?.adapter = mealAdapter
        recyclerViewCategory?.adapter = categoryAdapter
        recyclerViewMeal?.layoutManager = LinearLayoutManager(context)
        recyclerViewCategory?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter.clickListener = object : OnItemClickListenerCategory {
            override fun onItemClick(category: ModelCategory) {

                compositeDisposable.add(
                    mealsRepository.loadMealsData(category.nameCategory)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ mealList ->
                            mealAdapter.setList(mealList.meals)
                            sharedPreferences?.edit()?.putInt("id_category", category.idCategory)
                                ?.apply()

                            val buttonOpenFilterFragment =
                                requireActivity().findViewById<AppCompatButton>(R.id.img_filter_icon)
                                    .setOnClickListener {
                                        parentFragmentManager.beginTransaction()
                                            .replace(
                                                R.id.host_fragment, FilterFragment.newInstance()
                                            )
                                            .addToBackStack(null)
                                            .commit()

                                        val buttonSortAsc =
                                            requireActivity().findViewById<Button>(R.id.asc_filter)
                                                .setOnClickListener {
                                                    val mealSortedAsc =
                                                        mealList?.meals?.sortedByDescending { it.strMeal }
                                                            ?.reversed()
                                                    mealAdapter.setList(mealSortedAsc as MutableList<ModelMeal>)
                                                }
                                        val buttonSortDesc =
                                            requireActivity().findViewById<Button>(R.id.desc_filter)
                                                .setOnClickListener {
                                                    val mealSortedDesc =
                                                        mealList?.meals?.sortedByDescending { it.strMeal }
                                                    mealAdapter.setList(mealSortedDesc as MutableList<ModelMeal>)
                                                }
                                    }
                        },
                            {
                                it.printStackTrace()
                            })
                )

            }
        }
        mealAdapter.clickListener = clickListenerMeal


    }


    private fun showCategories(categoryItemList: List<ModelCategory>) =
        categoryAdapter.setList(categoryItemList)

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    companion object {
        fun newInstance(): MealListFragment = MealListFragment()
    }
}
