package com.creativeitinstitute.mvvmcleanecomrestapi.data.model.user

data class ResponseUser(
    val avatar: String,
    val creationAt: String,
    val email: String,
    val id: Int,
    val name: String,
    val password: String,
    val role: String,
    val updatedAt: String
)