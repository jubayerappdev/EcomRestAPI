package com.creativeitinstitute.mvvmcleanecomrestapi.data.repo

import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.RequestLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.network.PlatziService
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class PlatziRepository  @Inject constructor(private val service:PlatziService){

    suspend fun login(requestLogin: RequestLogin) : Response<ResponseBody>{

       return service.login(requestLogin)
    }
}