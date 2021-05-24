package com.example.weatherapp.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.Repository
import com.example.weatherapp.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) :
    ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussian = true)

    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussian = false)

    fun getWeatherFromRemoteSource() = getDataFromLocalSource(isRussian = true)

    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(if (isRussian) repositoryImpl.getWeatherFromLocalStorageRus() else repositoryImpl.getWeatherFromLocalStorageWorld()))
        }.start()
    }
}

//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.weatherapp.model.Repository
//import com.example.weatherapp.model.RepositoryImpl
//import java.lang.Thread.sleep
//
//class MainViewModel(
//    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
//    private val repositoryImpl: Repository = RepositoryImpl()
//) :
//    ViewModel() {
//
//    fun getLiveData() = liveDataToObserve
//
//    fun getWeatherFromLocalSource() = getDataFromLocalSource()
//
//    fun getWeatherFromRemoteSource() = getDataFromLocalSource()
//
//    private fun getDataFromLocalSource() {
//        liveDataToObserve.value = AppState.Loading
//        Thread {
//            sleep(1000)
//            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorage()))
//        }.start()
//    }
//}
