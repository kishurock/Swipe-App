package com.example.myapplication.data.repository

import com.example.myapplication.data.model.AddProductResponse
import com.example.myapplication.data.model.ProductListItem
import retrofit2.Response

interface ProductRemoteDataSource {
    suspend fun getProducts(): Response<List<ProductListItem>>
    suspend fun addProducts(productName:String?, productType:String, price:String?, tax:String?): Response<AddProductResponse>
}