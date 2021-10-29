package com.example.epam_internship_android_molodchenko.di.component

import com.example.epam_internship_android_molodchenko.di.module.AppModule
import com.example.epam_internship_android_molodchenko.di.module.BindModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, BindModule::class]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): AppComponent
    }
}