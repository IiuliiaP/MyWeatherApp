package com.example.myweatherapp.viewmodel

import com.example.myweatherapp.repository.Weather

sealed class AppState {
    object Loading: AppState()
    data class Success(val weatherData: Weather): AppState()
    data class Error(val error: Throwable): AppState()
}