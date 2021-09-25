package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import android.util.Log
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
    private val categoryRequestUseCase: RequestCategoryUseCase
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
                    mutableCategoryUIModel.value = it.map { it.toCategoryUIModel() }
                }, { it.printStackTrace() })
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