package com.creativeitinstitute.mvvmcleanecomrestapi.data.network


import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.RequestLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.ResponseLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.product.ResponseProductItem
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.user.ResponseUser
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.Constants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlatziService {
    @POST(Constants.LOGIN)
   suspend fun login(@Body requestLogin: RequestLogin):Response<ResponseLogin>

   @GET(Constants.PRODUCTS)
   suspend fun getAllProducts() : Response<List<ResponseProductItem>>



}