package com.example.appcitaexpress.data.remote

import com.example.appcitaexpress.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private val okHttpClient : OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }
    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }
    val specialtyApi : SpecialtyApi by lazy {
        retrofit.create(SpecialtyApi::class.java)
    }
    val doctorApi: DoctorApi by lazy {
        retrofit.create(DoctorApi::class.java)
    }
    val agendaApi: AgendaApi by lazy{
        retrofit.create(AgendaApi::class.java)
    }
    val slotAgendaApi: SlotAgendaApi by lazy{
        retrofit.create(SlotAgendaApi::class.java)
    }

}