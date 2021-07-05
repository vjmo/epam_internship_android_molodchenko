package com.example.epam_internship_android_molodchenko.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epam_internship_android_molodchenko.models.ModelCategory

import com.example.epam_internship_android_molodchenko.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    var myResponse: MutableLiveData<ModelCategory> = MutableLiveData()

    var myCustomCategory: MutableLiveData<ModelCategory> = MutableLiveData()
    var myCustomCategoryTwo: MutableLiveData<ModelCategory> = MutableLiveData()

    fun getCategories() {
        viewModelScope.launch {
            val response = repository.getCategories()
            myResponse.value = response
        }
    }

    fun getCustomCategory(){
        viewModelScope.launch {
            val response = repository.getCustomCategory()
        }
    }
}