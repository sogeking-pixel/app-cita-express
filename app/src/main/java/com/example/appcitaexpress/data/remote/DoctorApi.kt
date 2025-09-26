package com.example.appcitaexpress.data.remote

import com.example.appcitaexpress.data.model.Doctor
import retrofit2.http.GET
import retrofit2.http.Path

interface DoctorApi {
    @GET("/medicos/")
    suspend fun getDoctors(): List<Doctor>
    @GET("/medicos/{id}")
    suspend fun getDoctorById(@Path("id") id: Int): Doctor
}