package com.example.myapplication.presentation.di.teacher



import com.example.myapplication.presentation.fragments.ProductsFragment
import dagger.Subcomponent

@ProductScope
@Subcomponent(modules = [ProductModule::class])
interface ProductSubComponent {

    fun inject(productsFragment: ProductsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ProductSubComponent
    }
}