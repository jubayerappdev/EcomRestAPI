package com.creativeitinstitute.mvvmcleanecomrestapi.di

import com.creativeitinstitute.mvvmcleanecomrestapi.data.network.PlatziSecureService
import com.creativeitinstitute.mvvmcleanecomrestapi.data.network.PlatziService
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.AuthInterceptor
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(interceptor: AuthInterceptor): OkHttpClient{


        return OkHttpClient.Builder().addInterceptor(interceptor).build()

    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit.Builder): PlatziService{

        return retrofit.build().create(PlatziService::class.java)
    }

    @Provides
    @Singleton
    fun providesSecureService(okHttpClient: OkHttpClient, retrofit: Retrofit.Builder): PlatziSecureService{

        return retrofit.client(okHttpClient).build().create(PlatziSecureService::class.java)
    }


}