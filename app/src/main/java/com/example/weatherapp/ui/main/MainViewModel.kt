package com.example.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.AppState
import java.lang.Thread.sleep

// class MainViewModel(private val liveDataToObserve: MutableLiveData<Any> = MutableLiveData()) : // изначальный код
class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) : // код для отображения состояний
        ViewModel() {

//    fun getData(): LiveData<Any> { // открытый метод getData, который возвращает нашу LiveData всем, кто хочет подписаться
//        // на изменения данных. Тип объекта, который хранит в себе LiveData, — Any
//        return liveDataToObserve
//    }

    fun getLiveData() = liveDataToObserve

    fun getWeather() = getDataFromLocalSource()


//    fun getData(): LiveData<Any> {
//        getDataFromLocalSource()
//        return liveDataToObserve
//    }

    private fun getDataFromLocalSource() { // Мы добавили метод getDataFromLocalSource, который имитирует запрос к БД
        // или ещё какому-то источнику данных в приложении. Запрос осуществляется асинхронно в отдельном потоке.
        liveDataToObserve.value = AppState.Loading // добавили отбражение состояния загрузки
        Thread {
            sleep(1000)
           // liveDataToObserve.postValue(Any()) // Как только поток просыпается, мы передаём в нашу LiveData какие-то данные
        // через метод postValue. . Если данные передаются в основном потоке, используйте метод setValue
            liveDataToObserve.postValue(AppState.Success(Any()))
        }.start()
    }

}
