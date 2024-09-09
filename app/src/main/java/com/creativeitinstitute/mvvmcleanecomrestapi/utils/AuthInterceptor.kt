package com.creativeitinstitute.mvvmcleanecomrestapi.utils

import com.creativeitinstitute.mvvmcleanecomrestapi.prefs.PrefsManager
import com.creativeitinstitute.mvvmcleanecomrestapi.views.login.LoginFragment
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(val prefsManager: PrefsManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()

        request.addHeader(
            "Authorization",
            "Bearer ${prefsManager.getPrefs(LoginFragment.ACCESS_TOKEN)}"
        )

        val interceptor = chain.proceed(request.build())
        return interceptor

    }


}