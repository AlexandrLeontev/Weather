package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.app.App.Companion.getHistoryDao
import com.example.weatherapp.app.AppState
import com.example.weatherapp.repository.LocalRepositoryImpl

class HistoryViewModel(
        val historyLiveData: MutableLiveData<AppState> = MutableLiveData(),
        private val historyRepositoryImpl: LocalRepositoryImpl = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {

    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        historyLiveData.value = AppState.Success(historyRepositoryImpl.getAllHistory())
    }
}
