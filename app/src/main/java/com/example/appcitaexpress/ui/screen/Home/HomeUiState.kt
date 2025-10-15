package com.example.appcitaexpress.ui.screen.home

import com.example.appcitaexpress.data.model.Specialty

sealed interface HomeUiState {
    data class Success(val specialties: List<Specialty>) : HomeUiState
    object  Loading : HomeUiState
    object  Error : HomeUiState
}
