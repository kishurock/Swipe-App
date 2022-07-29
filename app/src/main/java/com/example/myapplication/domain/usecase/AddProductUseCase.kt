package com.example.myapplication.domain.usecase

import com.example.myapplication.data.model.AddProductResponse
import com.example.myapplication.domain.repository.ProductRepository

class AddProductUseCase (private val productRepository: ProductRepository) {
    suspend fun execute(productName:String?,productType:String,price:String?,tax:String?): AddProductResponse = productRepository.addProducts(productName,productType,price,tax)
}