package com.example.appcitaexpress.data.remote

import com.example.appcitaexpress.data.model.Agenda
import retrofit2.http.GET
import retrofit2.http.Path

interface AgendaApi {
    @GET("agendas/")
    suspend fun getAgendas(): List<Agenda>
    @GET("agendas/{id}")
    suspend fun getAgendaById(@Path("id") id: Int): Agenda
}