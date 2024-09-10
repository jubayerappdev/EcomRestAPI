package com.creativeitinstitute.mvvmcleanecomrestapi.data.repo

import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.RequestLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.ResponseLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.product.ResponseProductItem
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.user.ResponseUser
import com.creativeitinstitute.mvvmcleanecomrestapi.data.network.PlatziSecureService
import com.creativeitinstitute.mvvmcleanecomrestapi.data.network.PlatziService
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class PlatziRepository @Inject constructor(
    private val service: PlatziService,
    private val securedService: PlatziSecureService
) {

    suspend fun login(requestLogin: RequestLogin): Response<ResponseLogin> {

        return service.login(requestLogin)
    }

    suspend fun getProfile(): Response<ResponseUser> {

        return securedService.getProfile()
    }

    suspend fun getAllProducts(): Response<List<ResponseProductItem>>{

        return service.getAllProducts()
    }
}