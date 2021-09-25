package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.epam_internship_android_molodchenko.domain.useCase.GetMealDetailsUseCase
import com.example.epam_internship_android_molodchenko.presentation.mapper.toMealDetailsUIModel
import com.example.epam_internship_android_molodchenko.presentation.mapper.toMealUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.MealDetailsUIModel
import com.example.epam_internship_android_molodchenko.presentation.model.MealUIModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MealDetailsViewModel(private val mealDetailsUseCase: GetMealDetailsUseCase) : ViewModel() {

    private val mutableMealDetailsUIModel: MutableLiveData<List<MealDetailsUIModel>> =
        MutableLiveData()

    val mealUIModel: LiveData<List<MealDetailsUIModel>>
        get() = mutableMealDetailsUIModel

    private val compositeDisposable = CompositeDisposable()

    fun startReceivingMealDetails(idMeal: Int) {
        compositeDisposable.add(
            mealDetailsUseCase.invoke(idMeal)
                .map { mealDetailsEntity ->
                    mealDetailsEntity.toMealDetailsUIModel()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableMealDetailsUIModel.value = listOf(it) //Я НЕ ЗНАЮ НАСЧЕТ ЛИСТ ОФ
                }, { it.printStackTrace() })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}