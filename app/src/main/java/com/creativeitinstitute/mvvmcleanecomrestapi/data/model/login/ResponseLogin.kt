package com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResponseLogin(
    @SerializedName("access_token")
    val accessToken: String?=null,
    @SerializedName("refresh_token")
    val refreshToken: String?=null
)