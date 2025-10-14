package com.example.appcitaexpress.data.remote

import com.example.appcitaexpress.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-API-SECRET", BuildConfig.API_KEY)
            .build()
        return chain.proceed(request)
    }
}