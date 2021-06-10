package com.example.weatherapp.repository

import com.example.weatherapp.model.Weather

interface LocalRepository {
    fun getAllHistory(): List<Weather>
    fun saveEntity(weather: Weather)
}