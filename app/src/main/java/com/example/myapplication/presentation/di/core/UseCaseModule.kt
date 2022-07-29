package com.example.myapplication.presentation.di.core

import com.example.myapplication.domain.repository.ProductRepository
import com.example.myapplication.domain.usecase.AddProductUseCase
import com.example.myapplication.domain.usecase.GetProductUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideProductUseCae(productRepository: ProductRepository): GetProductUseCase {
        return GetProductUseCase(productRepository)
    }

    @Singleton
    @Provides
    fun provideAddProductUseCae(productRepository: ProductRepository): AddProductUseCase {
        return AddProductUseCase(productRepository)
    }
}