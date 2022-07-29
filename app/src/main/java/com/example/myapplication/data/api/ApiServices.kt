package com.example.myapplication.data.api


import com.example.myapplication.data.model.ProductListItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("get")
    suspend fun getProductList(): Response<List<ProductListItem>>
}