package com.example.epam_internship_android_molodchenko.di.component

import com.example.epam_internship_android_molodchenko.di.module.*
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealDetails.view.MealDetailsFragment
import com.example.epam_internship_android_molodchenko.presentation.feature.main.mealList.view.MealListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        BindModule::class,
        DatabaseModule::class,
        MealApiModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        SharedPreferencesModule::class
    ]
)
interface AppComponent {

    fun inject(mealListFragment: MealListFragment)
    fun inject(mealDetailsFragment: MealDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): AppComponent
    }
}