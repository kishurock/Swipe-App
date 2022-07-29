package com.example.myapplication.presentation.di.core

import com.example.myapplication.data.repository.ProductRemoteDataSource
import com.example.myapplication.data.repository.ProductRepositoryImpl
import com.example.myapplication.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class Repositorymodule {

    @Singleton
    @Provides
    fun provideProductRepository(productRemoteDataSource: ProductRemoteDataSource): ProductRepository {
        return ProductRepositoryImpl(productRemoteDataSource)

    }
}