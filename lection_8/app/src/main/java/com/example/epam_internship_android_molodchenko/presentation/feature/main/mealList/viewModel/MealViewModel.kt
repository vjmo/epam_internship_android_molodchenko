package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.epam_internship_android_molodchenko.data.preference.CategorySharedPreferences
import com.example.epam_internship_android_molodchenko.domain.useCase.GetMealListUseCase
import com.example.epam_internship_android_molodchenko.presentation.mapper.toMealUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.CategoryUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.MealUIModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.security.Provider

class MealViewModel(
    private val mealUseCase: GetMealListUseCase,
    private val sharedPreferences: SharedPreferences) : ViewModel() {

    private val mutableMealUIModel: MutableLiveData<List<MealUIModel>> = MutableLiveData()

    val mealUIModel: LiveData<List<MealUIModel>>
        get() = mutableMealUIModel

    private val compositeDisposable = CompositeDisposable()

    fun startReceivingMeal(categoryUIModel: CategoryUIModel, strCategory: String) {
        compositeDisposable.add(
            mealUseCase.invoke(strCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableMealUIModel.value = it.toMealUIModel()//Я НЕ ЗНАЮ НАСЧЕТ ЛИСТ ОФ
                    sharedPreferences.edit()
                        ?.putInt("id_category", categoryUIModel.id)
                        ?.apply()

                }, { it.printStackTrace() })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}