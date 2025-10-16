package com.example.appcitaexpress.ui.screen.homeui

import com.example.appcitaexpress.data.model.Specialty

data class HomeScreenData(
    val specialties : List<Specialty> = emptyList(),
    val selectedSpecialty : Specialty? = null,
    val selectedDate: String? = null
)