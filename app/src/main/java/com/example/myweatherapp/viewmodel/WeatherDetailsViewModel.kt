package com.example.myweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.repository.Repository
import com.example.myweatherapp.repository.RepositoryImpl
import com.example.myweatherapp.repository.Weather
import java.lang.Thread.sleep

class WeatherDetailsViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData(),
private val repository: RepositoryImpl= RepositoryImpl()) : ViewModel() {

    fun getData() : LiveData<AppState> {
        return liveData
    }
    fun getWeather(){
        Thread{
            liveData.postValue((AppState.Loading))
            if((0..10).random()> 5){
            liveData.postValue(AppState.Success(repository.getWeatherFromServer()))}
            else{
                liveData.postValue(AppState.Error(IllegalAccessError("ошибка")))
            }
        }.start()
    }

}