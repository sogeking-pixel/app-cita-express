package com.example.appcitaexpress.ui.screen.homeui

sealed interface HomeUiState {
    data class Success(val dataHome: HomeScreenData) : HomeUiState
    object  Loading : HomeUiState
    object  Error : HomeUiState
}
