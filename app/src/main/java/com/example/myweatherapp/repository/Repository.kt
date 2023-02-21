package com.example.myweatherapp.repository

interface Repository {
    fun getWeatherFromServer():Weather
    fun getWeatherFromLocal(): Weather
}