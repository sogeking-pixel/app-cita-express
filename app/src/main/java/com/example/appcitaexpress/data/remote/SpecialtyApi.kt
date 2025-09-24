package com.example.appcitaexpress.data.remote

import com.example.appcitaexpress.data.model.Specialty
import retrofit2.http.GET
import retrofit2.http.Path

interface SpecialtyApi {
    @GET("/especialidades/")
    suspend fun getSpecialties(): List<Specialty>
    @GET("/especialidades/{id}")
    suspend fun getSpecialtyById(@Path("id") id: Int): Specialty
}

