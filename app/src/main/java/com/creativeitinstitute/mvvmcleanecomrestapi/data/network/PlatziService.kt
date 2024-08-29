package com.creativeitinstitute.mvvmcleanecomrestapi.data.network


import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.RequestLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.Constants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PlatziService {
    @POST(Constants.LOGIN)
   suspend fun login(@Body requestLogin: RequestLogin):Response<ResponseBody>

}