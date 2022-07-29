package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.data.model.ProductListItem
import com.example.myapplication.domain.repository.ProductRepository
import retrofit2.Response
import java.lang.Exception

class ProductRepositoryImpl(private val productRemoteDataSource: ProductRemoteDataSource) :
    ProductRepository {
    override suspend fun getProducts(): List<ProductListItem> {
        return getProductFromApi()
    }

    suspend fun getProductFromApi(): List<ProductListItem> {
        lateinit var productListItem: List<ProductListItem>
        try {
            val response: Response<List<ProductListItem>> = productRemoteDataSource.getProducts()
            productListItem = response.body()!!
        } catch (exception: Exception) {
            Log.i("Mytag1", exception.message.toString())
        }
        return productListItem
    }
}