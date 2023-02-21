package com.example.myweatherapp.repository

import java.lang.Thread.sleep

class RepositoryImpl: Repository {
    override fun getWeatherFromServer(): Weather {
        Thread.sleep(2000L)
        return Weather()
    }

    override fun getWeatherFromLocal(): Weather {
        TODO("Not yet implemented")
    }
}