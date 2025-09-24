package com.example.appcitaexpress.data.remote

import com.example.appcitaexpress.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val baseUrl = BuildConfig.BASE_URL

object RetrofitClient {
    private val okHttpClient = OkHttpClient.Builder().
    addInterceptor(ApiKeyInterceptor())
        .build()
    private val retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    val specialtyApi : SpecialtyApi by lazy {
        retrofit.create(SpecialtyApi::class.java)
    }
}