package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.epam_internship_android_molodchenko.domain.useCase.GetMealListUseCase
import com.example.epam_internship_android_molodchenko.presentation.mapper.toMealUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.MealUIModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MealViewModel(private val mealUseCase: GetMealListUseCase) : ViewModel() {

    private val mutableMealUIModel: MutableLiveData<List<MealUIModel>> = MutableLiveData()

    val mealUIModel: LiveData<List<MealUIModel>>
        get() = mutableMealUIModel

    private val compositeDisposable = CompositeDisposable()

    fun startReceivingMeal(strCategory: String) {
        compositeDisposable.add(
            mealUseCase.invoke(strCategory)
                .map { mealEntity ->
                    mealEntity.toMealUIModel()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableMealUIModel.value = it //Я НЕ ЗНАЮ НАСЧЕТ ЛИСТ ОФ
                }, { it.printStackTrace() })
        )
    }

    private fun indexCC(){
        compositeDisposable.add(
            categoryRepository.observeCategory()
                .subscribeOn(Schedulers.io())
                .flatMap { list ->
                    val lastIndexCategory = sharedPreferences.getInt("id_category", 1)
                    val category = list[lastIndexCategory-1]
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
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}