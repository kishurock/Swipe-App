package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myapplication.data.model.AddProductResponse
import com.example.myapplication.domain.usecase.AddProductUseCase

class AddProductViewModel(private val addProductUseCase: AddProductUseCase) : ViewModel() {
    fun addProduct(productName:String?,productType:String,price:String?,tax:String?) = liveData{
        val addProductResponse: AddProductResponse =  addProductUseCase.execute(productName,productType,price,tax)
        emit(addProductResponse)
    }
}