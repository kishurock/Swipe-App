package com.example.myapplication.presentation.di.teacher

import com.example.myapplication.domain.usecase.AddProductUseCase
import com.example.myapplication.presentation.viewmodel.MainViewModelFactory
import com.example.myapplication.domain.usecase.GetProductUseCase
import com.example.myapplication.presentation.viewmodel.AddProductViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ProductModule {
    @ProductScope
    @Provides
    fun provideProductViewModelFactory(getProductUseCase: GetProductUseCase): MainViewModelFactory {
        return MainViewModelFactory(getProductUseCase)
    }

    @ProductScope
    @Provides
    fun provideAddProductViewModelFactory(addProductUseCase: AddProductUseCase): AddProductViewModelFactory {
        return AddProductViewModelFactory(addProductUseCase)
    }
}