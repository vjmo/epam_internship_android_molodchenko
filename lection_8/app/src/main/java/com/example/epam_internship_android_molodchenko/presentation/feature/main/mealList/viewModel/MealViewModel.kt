package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.epam_internship_android_molodchenko.domain.useCase.GetCategoryUseCase
import com.example.epam_internship_android_molodchenko.domain.useCase.GetMealListUseCase
import com.example.epam_internship_android_molodchenko.domain.useCase.RequestCategoryUseCase
import com.example.epam_internship_android_molodchenko.presentation.mapper.toCategoryUIModel
import com.example.epam_internship_android_molodchenko.presentation.mapper.toMealUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.CategoryUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.MealUIModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MealViewModel(
    private val mealUseCase: GetMealListUseCase,
    private val categoryUseCase: GetCategoryUseCase,
    private val categoryRequestUseCase: RequestCategoryUseCase
) : ViewModel() {

    private val mutableMealUIModel: MutableLiveData<List<MealUIModel>> = MutableLiveData()
    private val mutableCategoryUIModel: MutableLiveData<List<CategoryUIModel>> = MutableLiveData()

    val mealUIModel: LiveData<List<MealUIModel>>
        get() = mutableMealUIModel

    val categoryUIModel: LiveData<List<CategoryUIModel>>
        get() = mutableCategoryUIModel

    private val compositeDisposable = CompositeDisposable()

    fun startReceivingMeal(strCategory: String) {
        compositeDisposable.add(
            mealUseCase.invoke(strCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ model ->
                    mutableMealUIModel.value = model.toMealUIModel()
                }, { it.printStackTrace() })
        )
    }

    fun start() {
        observeCategory()
        requestCategory()
    }

    private fun observeCategory() {
        compositeDisposable.add(
            categoryUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableCategoryUIModel.value = it.toCategoryUIModel()
                },
                    { it.printStackTrace() })
        )
    }

    private fun requestCategory() {
        compositeDisposable.add(
            categoryRequestUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, { it.printStackTrace() })
        )
    }



    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}