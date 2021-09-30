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
import com.example.epam_internship_android_molodchenko.presentation.model.MealUIModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.security.Provider

class MealViewModel(private val mealUseCase: GetMealListUseCase) : ViewModel() {

    private val mutableMealUIModel: MutableLiveData<List<MealUIModel>> = MutableLiveData()

    val mealUIModel: LiveData<List<MealUIModel>>
        get() = mutableMealUIModel

    private val compositeDisposable = CompositeDisposable()

    fun startReceivingMeal(strCategory: String) {
        compositeDisposable.add(
            mealUseCase.invoke(strCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableMealUIModel.value = it.toMealUIModel()//Я НЕ ЗНАЮ НАСЧЕТ ЛИСТ ОФ
                }, { it.printStackTrace() })
        )
    }

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            "settings_prefs",
            Context.MODE_PRIVATE
        )
    }

    private fun indexCC(){
        compositeDisposable.add(
            mealUseCase.invoke(strCategory = ).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .flatMap { list ->
                    val lastIndexCategory = sharedPreferences.getInt("id_category", 1)
                    val category = list[lastIndexCategory-1]
                    return@flatMap mealUseCase.invoke(category.nameCategory)
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
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}