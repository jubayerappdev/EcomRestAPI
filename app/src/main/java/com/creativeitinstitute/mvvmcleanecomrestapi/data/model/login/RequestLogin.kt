package com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login

import androidx.annotation.Keep

@Keep
data class RequestLogin(
    val email: String,
    val password: String
)