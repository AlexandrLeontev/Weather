package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.app.App.Companion.getHistoryDao
import com.example.weatherapp.app.AppState
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherDTO
import com.example.weatherapp.repository.DetailsRepository
import com.example.weatherapp.repository.DetailsRepositoryImpl
import com.example.weatherapp.repository.LocalRepositoryImpl
import com.example.weatherapp.repository.RemoteDataSource
import com.example.weatherapp.utils.convertDtoToModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class DetailsViewModel(
        val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
        private val detailsRepositoryImpl: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource()),
        private val historyRepositoryImpl: LocalRepositoryImpl = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {

    fun getWeatherFromRemoteSource(lat: Double, lon: Double) {
        detailsLiveData.value = AppState.Loading
        detailsRepositoryImpl.getWeatherDetailsFromServer(lat, lon, callBack)
    }

    fun saveCityToDB(weather: Weather) {
        historyRepositoryImpl.saveEntity(weather)
    }

    private val callBack = object :
            Callback<WeatherDTO> {

        override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
            val serverResponse: WeatherDTO? = response.body()
            detailsLiveData.postValue(
                    if (response.isSuccessful && serverResponse != null) {
                        checkResponse(serverResponse)
                    } else {
                        AppState.Error(Throwable(SERVER_ERROR))
                    }
            )
        }

        override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: WeatherDTO): AppState {
            val fact = serverResponse.fact
            return if (fact == null || fact.temp == null || fact.feels_like == null || fact.condition.isNullOrEmpty()) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(convertDtoToModel(serverResponse))
            }
        }
    }
}
