package com.example.myapplication.domain.repository

import com.example.myapplication.data.model.ProductListItem

interface ProductRepository {

    suspend fun getProducts(): List<ProductListItem>
}