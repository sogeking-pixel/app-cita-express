package com.example.appcitaexpress.data.model

data class Agenda(
    var id: Int,
    var codeUnique: String,
    var date: String?,
    var startTime: String?,
    var startEnd: String?,
    var timeSlotMinutes: Int,
    var medicoEspecialidad: MedicoEspecialidad,
)