package com.creativeitinstitute.mvvmcleanecomrestapi.di

import com.creativeitinstitute.mvvmcleanecomrestapi.data.network.PlatziService
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

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


}