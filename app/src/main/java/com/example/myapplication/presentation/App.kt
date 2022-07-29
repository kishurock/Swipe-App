package com.example.myapplication.presentation


import android.app.Application
import com.example.myapplication.BuildConfig
import com.example.myapplication.presentation.di.Injector
import com.example.myapplication.presentation.di.core.AppComponent
import com.example.myapplication.presentation.di.core.AppModule
import com.example.myapplication.presentation.di.core.DaggerAppComponent
import com.example.myapplication.presentation.di.core.NetModule
import com.example.myapplication.presentation.di.teacher.ProductSubComponent


class App : Application(), Injector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .build()
    }

    override fun createProductSubComponent(): ProductSubComponent {
        return appComponent.productSubComponent().create()
    }


}