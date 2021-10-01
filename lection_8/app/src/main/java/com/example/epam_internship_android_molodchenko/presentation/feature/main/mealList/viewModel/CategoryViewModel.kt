package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.epam_internship_android_molodchenko.domain.useCase.GetCategoryUseCase
import com.example.epam_internship_android_molodchenko.domain.useCase.RequestCategoryUseCase
import com.example.epam_internship_android_molodchenko.presentation.mapper.toCategoryUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.CategoryUIModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoryViewModel(
    private val categoryUseCase: GetCategoryUseCase,
    private val categoryRequestUseCase: RequestCategoryUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val mutableCategoryUIModel: MutableLiveData<List<CategoryUIModel>> = MutableLiveData()

    val categoryUIModel: LiveData<List<CategoryUIModel>>
        get() = mutableCategoryUIModel

    private val compositeDisposable = CompositeDisposable()

    fun startReceivingCategory() {
        compositeDisposable.add(
            categoryUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableCategoryUIModel.value = it.toCategoryUIModel()
                    val getIndexCategory = sharedPreferences.getInt("id_category", 1)
                    val lastCategory = it[getIndexCategory-1]
                    sharedPreferences.edit()
                        ?.putInt("id_category", lastCategory.id)
                        ?.apply()
                },
                    { it.printStackTrace() })
        )
    }

    fun startRequestCategory() {
        compositeDisposable.add(
            categoryRequestUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("MealListFragment", "Request")
                }, { it.printStackTrace() })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}