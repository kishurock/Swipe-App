package com.example.myapplication.presentation.di.core

import com.example.myapplication.data.api.ApiServices
import com.example.myapplication.data.repository.ProductRemoteDataSource
import com.example.myapplication.data.repository.ProductRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(apiServices: ApiServices): ProductRemoteDataSource {
        return ProductRemoteDataSourceImpl(apiServices)

    }
}