package com.example.weatherapp.repository

import com.example.weatherapp.model.Weather
import com.example.weatherapp.room.HistoryDao
import com.example.weatherapp.utils.convertHistoryEntityToWeather
import com.example.weatherapp.utils.convertWeatherToEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) :
        LocalRepository {

    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        localDataSource.insert(convertWeatherToEntity(weather))
    }
}