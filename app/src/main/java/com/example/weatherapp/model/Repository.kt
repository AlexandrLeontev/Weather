package com.example.weatherapp.model

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}

//interface Repository {
//    fun getWeatherFromServer(): Weather
//    fun getWeatherFromLocalStorage(): Weather
//}
