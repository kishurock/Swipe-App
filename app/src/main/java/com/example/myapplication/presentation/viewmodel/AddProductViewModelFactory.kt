package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.usecase.AddProductUseCase

class AddProductViewModelFactory (private val addProductUseCase: AddProductUseCase):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddProductViewModel(addProductUseCase) as T
    }
}