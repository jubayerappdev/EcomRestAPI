package com.creativeitinstitute.mvvmcleanecomrestapi.data.model.product

data class ResponseProductItem(
    val category: Category,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String,
    val updatedAt: String
)