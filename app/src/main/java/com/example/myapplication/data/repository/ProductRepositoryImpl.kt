package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.data.model.AddProductResponse
import com.example.myapplication.data.model.ProductListItem
import com.example.myapplication.domain.repository.ProductRepository
import retrofit2.Response
import java.lang.Exception

class ProductRepositoryImpl(private val productRemoteDataSource: ProductRemoteDataSource) :
    ProductRepository {
    override suspend fun getProducts(): List<ProductListItem> {
        return getProductFromApi()
    }

    override suspend fun addProducts(
        productName: String?,
        productType: String,
        price: String?,
        tax: String?
    ): AddProductResponse {
        return addProductsInApi(productName,productType,price,tax)
    }

    suspend fun addProductsInApi(productName: String?, productType: String, price: String?, tax: String?): AddProductResponse {
        lateinit var addProductResponse: AddProductResponse
        try {
            val response: Response<AddProductResponse> = productRemoteDataSource.addProducts(productName,productType,price,tax)
            addProductResponse = response.body()!!
        } catch (exception: Exception) {
            Log.i("Mytag2", exception.message.toString())
        }
        return addProductResponse
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