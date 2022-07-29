package com.example.myapplication.data.repository

import com.example.myapplication.data.api.ApiServices
import com.example.myapplication.data.model.ProductListItem
import retrofit2.Response

class ProductRemoteDataSourceImpl(private val apiServices: ApiServices) : ProductRemoteDataSource {

    override suspend fun getProducts(): Response<List<ProductListItem>> =
        apiServices.getProductList()



}