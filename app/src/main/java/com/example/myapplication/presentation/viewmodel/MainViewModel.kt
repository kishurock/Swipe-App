package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myapplication.data.model.ProductListItem
import com.example.myapplication.domain.usecase.GetProductUseCase

class MainViewModel(private val productUseCase: GetProductUseCase) : ViewModel() {
    // TODO: Implement the ViewModel
    fun getProducts() = liveData {
        val productList: List<ProductListItem> = productUseCase.execute()
        emit(productList)
    }


}