package com.example.appcitaexpress.data.remote

import com.example.appcitaexpress.data.model.SlotAgenda
import retrofit2.http.GET
import retrofit2.http.Path

interface SlotAgendaApi {
    @GET("/agendas/{agenda_id}/slots/")
    suspend fun getSlotsByIdAgenda(@Path("agenda_id") idAgenda: Int): List<SlotAgenda>
}