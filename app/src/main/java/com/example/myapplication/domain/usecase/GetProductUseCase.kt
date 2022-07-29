package com.example.myapplication.domain.usecase

import com.example.myapplication.data.model.ProductListItem
import com.example.myapplication.domain.repository.ProductRepository

class GetProductUseCase(private val productRepository: ProductRepository) {
    suspend fun execute(): List<ProductListItem> = productRepository.getProducts()
}
