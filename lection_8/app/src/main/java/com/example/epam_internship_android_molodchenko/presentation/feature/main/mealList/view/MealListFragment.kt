package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
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
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel.MealViewModel
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel.MealViewModelFactory
import com.example.epam_internship_android_molodchenko.presentation.model.CategoryUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.MealUIModel
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class MealListFragment : Fragment() {

    private lateinit var viewBinding: FragmentMealListBinding

    private val sharedPreferences: SharedPreferences by lazy { TestApp.INSTANCE.sp }

    private val viewModelMeal: MealViewModel by viewModels {
        MealViewModelFactory(
            GetMealListUseCase(
                MealsRepositoryImpl(
                    RetrofitInstance.mealApi
                )
            ), GetCategoryUseCase(
                CategoryRepositoryImpl(
                    RetrofitInstance.mealApi, TestApp.INSTANCE.db, sharedPreferences
                )
            ),
            RequestCategoryUseCase(
                CategoryRepositoryImpl(
                    RetrofitInstance.mealApi, TestApp.INSTANCE.db, sharedPreferences
                )
            )
        )
    }

    private val fragment = MealFilterFragment.newInstance()

    private val mealAdapter = MealAdapter()
    private val categoryAdapter = CategoryAdapter()

    private val recyclerViewCategory by lazy { viewBinding.rvCategory }
    private val recyclerViewMeal by lazy { viewBinding.rvMeal }

    private val toolbarList: Toolbar? by lazy { viewBinding.toolbarList }

    private val compositeDisposable = CompositeDisposable()

    private val clickListenerMeal = object : OnItemClickListenerMeal {
        override fun onItemClick(mealUI: MealUIModel) {
             //   findNavController().navigate(R.id.action_mealListFragment_to_mealDetailsFragment, bundleOf(ID to mealUI.id))
            parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.host_fragment, MealDetailsFragment.newInstance(mealUI.id)
                    )
                    .addToBackStack(null)
                    .commit()
        }
/*
             view?.setOnClickListener {
                 findNavController().navigate(
                     R.id.action_mealListFragment_to_mealDetailsFragment,
                     bundleOf(ID to mealUI.id)
                 )
             }*/

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
        initView()
        viewModelMeal.start()
        viewModelMeal.categoryUIModel.observe(viewLifecycleOwner, {
            categoryAdapter.setList(it)
        })
        viewModelMeal.mealUIModel.observe(viewLifecycleOwner, {
            mealAdapter.setList(it)
        })
    }

    private fun initView() {

        recyclerViewMeal.adapter = mealAdapter
        recyclerViewCategory.adapter = categoryAdapter
        recyclerViewMeal.layoutManager = LinearLayoutManager(context)
        recyclerViewCategory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

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
                viewModelMeal.startReceivingMeal(categoryUI.title)
            }
        }
        mealAdapter.clickListener = clickListenerMeal
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    companion object {
        private const val ID = "ID"
        fun newInstance(): MealListFragment = MealListFragment()
    }


}
