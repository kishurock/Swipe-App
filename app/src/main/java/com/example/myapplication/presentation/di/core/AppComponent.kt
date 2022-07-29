package com.example.myapplication.presentation.di.core

import com.example.myapplication.presentation.di.teacher.ProductSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        RemoteDataModule::class,
        UseCaseModule::class,
        Repositorymodule::class
    ]
)
interface AppComponent {

    fun productSubComponent(): ProductSubComponent.Factory

}