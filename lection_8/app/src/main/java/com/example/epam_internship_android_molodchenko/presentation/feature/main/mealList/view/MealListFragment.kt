package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epam_internship_android_molodchenko.*
import com.example.epam_internship_android_molodchenko.data.network.RetrofitInstance
import com.example.epam_internship_android_molodchenko.data.repository.CategoryRepositoryImpl
import com.example.epam_internship_android_molodchenko.data.repository.MealsRepositoryImpl
import com.example.epam_internship_android_molodchenko.databinding.FragmentMealListBinding
import com.example.epam_internship_android_molodchenko.domain.useCase.GetCategoryUseCase
import com.example.epam_internship_android_molodchenko.domain.useCase.GetMealListUseCase
import com.example.epam_internship_android_molodchenko.domain.useCase.RequestCategoryUseCase
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealDetails.view.MealDetailsFragment
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealFilter.view.MealFilterFragment
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealFilter.view.clickListener.OnItemClickListenerFilter
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.adapter.CategoryAdapter
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.adapter.MealAdapter
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener.OnItemClickListenerCategory
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.clickListener.OnItemClickListenerMeal
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel.CategoryViewModel
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel.CategoryViewModelFactory
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel.MealViewModel
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel.MealViewModelFactory
import com.example.epam_internship_android_molodchenko.presentation.model.CategoryUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.MealUIModel
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class MealListFragment : Fragment() {
    // ресайкл и адаптер остаются во фрагменет ( что отвечает за отображение, то и остается)

    private lateinit var viewBinding: FragmentMealListBinding

    private val viewModelMeal: MealViewModel by viewModels {
        MealViewModelFactory(
            GetMealListUseCase(
                MealsRepositoryImpl(
                    (RetrofitInstance.mealApi)
                )
            )
        )
    }

    private val viewModelCategory: CategoryViewModel by viewModels {
        CategoryViewModelFactory(
            GetCategoryUseCase(
                CategoryRepositoryImpl(
                    (RetrofitInstance.mealApi), (TestApp.INSTANCE.db)
                )
            ),
            RequestCategoryUseCase(
                CategoryRepositoryImpl(
                    (RetrofitInstance.mealApi), (TestApp.INSTANCE.db)
                )
            )
        )
    }

    private val mealAdapter = MealAdapter()
    private val categoryAdapter = CategoryAdapter()

    private val recyclerViewCategory by lazy { viewBinding.rvCategory }
    private val recyclerViewMeal by lazy { viewBinding.rvMeal }

    private val toolbarList: Toolbar? by lazy { viewBinding.toolbarList }

    private val sharedPreferences: SharedPreferences by lazy {
        requireContext().getSharedPreferences(
            "settings_prefs",
            Context.MODE_PRIVATE
        )
    }

    //конструктор
    private val compositeDisposable = CompositeDisposable()//экземляр
    /*  private val categoryRepository by lazy {
          CategoryRepositoryImpl(
              RetrofitInstance.mealApi,
              TestApp.INSTANCE.db
          )
      }
      private val mealsRepository by lazy { MealsRepositoryImpl(RetrofitInstance.mealApi) }*/


    private val clickListenerMeal = object : OnItemClickListenerMeal {
        override fun onItemClick(mealUI: MealUIModel) {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.host_fragment, MealDetailsFragment.newInstance(mealUI.id)
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

        viewBinding = FragmentMealListBinding.inflate(inflater, container, false)
        //  return inflater.inflate(R.layout.fragment_meal_details, container, false)
        return viewBinding.root
        // return inflater.inflate(R.layout.fragment_meal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)

// я думаю, что это дублирование, посиди пока так
        /*compositeDisposable.add(
            categoryRepository.requestCategories()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.d("MealListFragment", "Success")
                }, {
                    it.printStackTrace()
                })
        )*/


        //выношу в VM
        viewModelCategory.startReceivingCategory()
        viewModelCategory.categoryUIModel.observe(viewLifecycleOwner, {
            val getIndexCategory = sharedPreferences.getInt("id_category", 1)
            val lastCategory = it[getIndexCategory - 1]

            viewModelMeal.mealUIModel.observe(viewLifecycleOwner, {
                viewModelMeal.startReceivingMeal(lastCategory.title)
                sharedPreferences.edit()
                    ?.putInt("id_category", lastCategory.id)
                    ?.apply()
                mealAdapter.setList(it)
            })
            categoryAdapter.setList(it)
        })
        /*compositeDisposable.add(
            categoryRepository.observeCategory()
                .subscribeOn(Schedulers.io())
                .flatMap { list ->
                    val lastIndexCategory = sharedPreferences.getInt("id_category", 1)
                    val category = list[lastIndexCategory - 1]
                    return@flatMap mealsRepository.loadMealsData(category.nameCategory)
                        .doOnSuccess {
                            sharedPreferences.edit()
                                ?.putInt("id_category", category.idCategory)
                                ?.apply()
                        }
                        .map { Pair(list, it) }
                        .toFlowable()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    categoryAdapter.setList(it.first)
                    mealAdapter.setList(it.second.mealDtos)
                }, {
                    it.printStackTrace()
                })
        )
        */
    }

    private fun initView(view: View) {

        recyclerViewMeal.adapter = mealAdapter
        recyclerViewCategory.adapter = categoryAdapter
        recyclerViewMeal.layoutManager = LinearLayoutManager(context)
        recyclerViewCategory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val fragment = MealFilterFragment.newInstance()

        toolbarList?.inflateMenu(R.menu.main_menu)
        toolbarList?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.filter -> fragment.show(childFragmentManager, null)
            }
            return@setOnMenuItemClickListener true
        }

        fragment.clickListener = object : OnItemClickListenerFilter {
            override fun onItemClick(active: Boolean) {
                mealAdapter.sortedByAscOrDesc(active)
            }
        }

//VM
        categoryAdapter.clickListener = object : OnItemClickListenerCategory {
            override fun onItemClick(categoryUI: CategoryUIModel) {
                viewModelMeal.startReceivingMeal(categoryUI.title)
                viewModelMeal.mealUIModel.observe(viewLifecycleOwner, {
                    mealAdapter.setList(it)
                    sharedPreferences.edit()?.putInt("id_category", categoryUI.id)
                        ?.apply()
                })
                /*compositeDisposable.add(
                    mealsRepository.loadMealsData(categoryDb.nameCategory)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ mealList ->
                            mealAdapter.setList(mealList.mealDtos)
                            sharedPreferences.edit()?.putInt("id_category", categoryDb.idCategory)
                                ?.apply()
                        },
                            {
                                it.printStackTrace()
                            })
                )*/
            }
        }
        mealAdapter.clickListener = clickListenerMeal
    }


    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    companion object {
        fun newInstance(): MealListFragment = MealListFragment()
    }
}
