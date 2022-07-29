package com.example.myapplication.data.api


import com.example.myapplication.data.model.AddProductResponse
import com.example.myapplication.data.model.ProductListItem
import com.google.gson.JsonObject
import org.w3c.dom.Text
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {

    @GET("get")
    suspend fun getProductList(): Response<List<ProductListItem>>


    @FormUrlEncoded
    @POST("add")
    suspend fun addProduct(@Field("product_name") productName:String?,
                           @Field("product_type") productType:String,
                           @Field("price") price:String?,
                           @Field("tax") tax:String?,
    ): Response<AddProductResponse>


}