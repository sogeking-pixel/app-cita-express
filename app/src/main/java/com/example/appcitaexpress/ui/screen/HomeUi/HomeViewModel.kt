package com.example.appcitaexpress.ui.screen.homeui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcitaexpress.data.model.Specialty
import com.example.appcitaexpress.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        loadSpecialties()
    }

    fun loadSpecialties() {
        viewModelScope.launch {
            Log.d("HomeViewModel", "loadSpecialties: Loading")
            _homeUiState.value = HomeUiState.Loading
            _homeUiState.value = try {
                val resultPaginator = RetrofitClient.specialtyApi.getSpecialties()
                Log.d("HomeViewModel", "loadSpecialties: Suceess")

                HomeUiState.Success(HomeScreenData(specialties = resultPaginator.results))
            } catch (e: IOException) {
                Log.d("HomeViewModel", "loadSpecialties: Error ${e.message}")

                HomeUiState.Error

            }
        }
    }

    fun onSpecialtySelected(specialty: Specialty) {
        val currentState = _homeUiState.value
        if (currentState !is HomeUiState.Success) return
        _homeUiState.value = currentState.copy(
            dataHome = currentState.dataHome.copy(selectedSpecialty = specialty)
        )
    }

    fun onDateSelected(date: String) {
        val currentState = _homeUiState.value
        if (currentState !is HomeUiState.Success) return
        _homeUiState.value = currentState.copy(
            dataHome = currentState.dataHome.copy(selectedDate = date)
        )
    }

    fun isFormValid(): Boolean {
        val currentState = _homeUiState.value
        if (currentState !is HomeUiState.Success) return false
        if (currentState.dataHome.selectedSpecialty == null) return false
        if (currentState.dataHome.selectedDate == null) return false
        return true
    }

    fun searchDoctors() {
        // xd
    }
}
