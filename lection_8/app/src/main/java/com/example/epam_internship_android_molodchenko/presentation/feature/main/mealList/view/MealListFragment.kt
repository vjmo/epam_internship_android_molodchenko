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

    private lateinit var viewBinding: FragmentMealListBinding

    private val viewModelMeal: MealViewModel by viewModels {
        MealViewModelFactory(
            GetMealListUseCase(
                MealsRepositoryImpl(
                    (RetrofitInstance.mealApi)
                )
            ), sharedPreferences
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
            ), sharedPreferences
        )
    }

    private val sharedPreferences: SharedPreferences by lazy {
        requireContext().getSharedPreferences(
            "settings_prefs",
            Context.MODE_PRIVATE
        )
    }

    private val mealAdapter = MealAdapter()
    private val categoryAdapter = CategoryAdapter()

    private val recyclerViewCategory by lazy { viewBinding.rvCategory }
    private val recyclerViewMeal by lazy { viewBinding.rvMeal }

    private val toolbarList: Toolbar? by lazy { viewBinding.toolbarList }

    private val compositeDisposable = CompositeDisposable()//экземляр

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
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)

        viewModelCategory.startReceivingCategory()
        viewModelCategory.categoryUIModel.observe(viewLifecycleOwner, {
            categoryAdapter.setList(it)
        })
        viewModelCategory.startRequestCategory()

        viewModelMeal.mealUIModel.observe(viewLifecycleOwner, {
            mealAdapter.setList(it)
        })
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

        categoryAdapter.clickListener = object : OnItemClickListenerCategory {
            override fun onItemClick(categoryUI: CategoryUIModel) {
                viewModelMeal.startReceivingMeal(categoryUI, categoryUI.title)
                viewModelMeal.mealUIModel.observe(viewLifecycleOwner, {
                    mealAdapter.setList(it)
                })
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
