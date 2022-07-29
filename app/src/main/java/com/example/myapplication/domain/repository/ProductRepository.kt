package com.example.myapplication.domain.repository

import com.example.myapplication.data.model.AddProductResponse
import com.example.myapplication.data.model.ProductListItem
import retrofit2.Response

interface ProductRepository {

    suspend fun getProducts(): List<ProductListItem>
    suspend fun addProducts(productName:String?, productType:String, price:String?, tax:String?): AddProductResponse
}