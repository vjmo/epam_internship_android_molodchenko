package com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.epam_internship_android_molodchenko.data.preferences.SharedPreferencesProvider
import com.example.epam_internship_android_molodchenko.data.repository.MealsRepositoryImpl
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
import javax.inject.Inject

class MealViewModel @Inject constructor(
    private val mealUseCase: GetMealListUseCase,
    private val categoryUseCase: GetCategoryUseCase,
    private val categoryRequestUseCase: RequestCategoryUseCase,
    private val sp: SharedPreferencesProvider
) : ViewModel() {

    private val mutableMealUIModel: MutableLiveData<List<MealUIModel>> = MutableLiveData()
    private val mutableCategoryUIModel: MutableLiveData<List<CategoryUIModel>> = MutableLiveData()
    private val mutableTitleCategory: MutableLiveData<String> = MutableLiveData()

    val mealUIModel: LiveData<List<MealUIModel>>
        get() = mutableMealUIModel

    val categoryUIModel: LiveData<List<CategoryUIModel>>
        get() = mutableCategoryUIModel

    val titleCategory: LiveData<String>
        get() = mutableTitleCategory

    private val compositeDisposable = CompositeDisposable()

    private fun startReceivingMeal(strCategory: String) {
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
        val categorySharedPreferences = sp.str(MealsRepositoryImpl.KEY_CATEGORY, null)

        if (categorySharedPreferences != null) {
            saveLastCategory(categorySharedPreferences)
            observeCategory(categorySharedPreferences)
        }


        requestCategory()
    }

    fun saveLastCategory(strCategory: String) {
        mutableTitleCategory.value = strCategory
        startReceivingMeal(strCategory)
    }

    private fun observeCategory(strCategory: String) {
        compositeDisposable.add(
            categoryUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableCategoryUIModel.value = it.toCategoryUIModel().map { categoryUIModel ->
                        categoryUIModel.copy(activeCategory = strCategory == categoryUIModel.title)
                    }
                },
                    { it.printStackTrace() })
        )
    }

    private fun requestCategory() {
        compositeDisposable.add(
            categoryRequestUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { it.printStackTrace() })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}