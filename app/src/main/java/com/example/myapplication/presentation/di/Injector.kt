package com.example.myapplication.presentation.di

import com.example.myapplication.presentation.di.teacher.ProductSubComponent

interface Injector {
    fun createProductSubComponent(): ProductSubComponent
}