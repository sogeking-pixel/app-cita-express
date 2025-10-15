package com.example.appcitaexpress.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcitaexpress.BuildConfig
import com.example.appcitaexpress.data.model.Specialty
import com.example.appcitaexpress.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
            _homeUiState.value = HomeUiState.Loading
            _homeUiState.value = try {
                val resultPaginator = RetrofitClient.specialtyApi.getSpecialties()
                HomeUiState.Success(resultPaginator.results)
            } catch (e: IOException) {
                HomeUiState.Error
            }
        }
    }

    fun onSpecialtySelected(specialty: Specialty) {
//        _homeState.update { currentState ->
//            currentState.copy(selectedSpecialty = specialty)
//        }
    }

    fun onDateSelected(date: String) {
//        _homeState.update { currentState ->
//            currentState.copy(selectedDate = date)
//        }
    }

    fun searchDoctors() {
        // xd
    }
}
