package com.example.epam_internship_android_molodchenko.di.module

import android.content.Context
import com.example.epam_internship_android_molodchenko.di.component.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContext(application: App):Context = application.baseContext
}