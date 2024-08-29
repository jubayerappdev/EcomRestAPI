package com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login

data class ResponseLogin(
    val access_token: String,
    val refresh_token: String
)