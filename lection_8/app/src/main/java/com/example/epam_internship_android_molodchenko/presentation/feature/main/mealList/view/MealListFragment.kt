package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epam_internship_android_molodchenko.*
import com.example.epam_internship_android_molodchenko.databinding.FragmentMealListBinding
import com.example.epam_internship_android_molodchenko.di.component.App
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
import javax.inject.Inject

class MealListFragment : Fragment() {

    @Inject
    lateinit var mealViewModelFactory: MealViewModelFactory

    private lateinit var viewBinding: FragmentMealListBinding

    private val viewModelMeal: MealViewModel by viewModels {
        mealViewModelFactory
    }

    private val fragment = MealFilterFragment.newInstance()

    private val mealAdapter = MealAdapter()
    private val categoryAdapter = CategoryAdapter()

    private val recyclerViewCategory by lazy { viewBinding.rvCategory }
    private val recyclerViewMeal by lazy { viewBinding.rvMeal }

    private val toolbarList: Toolbar? by lazy { viewBinding.toolbarList }

    private val clickListenerMeal = object : OnItemClickListenerMeal {
        override fun onItemClick(mealUI: MealUIModel) {
            findNavController().navigate(
                R.id.action_mealListFragment_to_mealDetailsFragment,
                bundleOf(ID to mealUI.id)
            )
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
        App.INSTANCE.component.inject(this)
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
        super.onDestroy()
    }

    companion object {
        private const val ID = "ID"
        fun newInstance(): MealListFragment = MealListFragment()
    }


}
