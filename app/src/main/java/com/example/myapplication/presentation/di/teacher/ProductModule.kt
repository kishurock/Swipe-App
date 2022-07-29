package com.example.myapplication.presentation.di.teacher

import com.example.myapplication.presentation.viewmodel.MainViewModelFactory
import com.example.myapplication.domain.usecase.GetProductUseCase
import dagger.Module
import dagger.Provides

@Module
class ProductModule {
    @ProductScope
    @Provides
    fun provideProductViewModelFactory(getProductUseCase: GetProductUseCase): MainViewModelFactory {
        return MainViewModelFactory(getProductUseCase)
    }
}