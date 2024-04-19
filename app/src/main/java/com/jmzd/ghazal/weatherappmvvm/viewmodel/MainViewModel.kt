package com.jmzd.ghazal.weatherappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesEntity
import com.jmzd.ghazal.weatherappmvvm.data.model.add_city.ResponseCitiesList
import com.jmzd.ghazal.weatherappmvvm.data.model.main.ResponseCurrentWeather
import com.jmzd.ghazal.weatherappmvvm.data.repository.MainRepository
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    //Cities from database
    private val _citiesLiveData = MutableLiveData<List<CitiesEntity>>()
    val citiesLiveData: LiveData<List<CitiesEntity>> = _citiesLiveData

    //current weather
    private val _currentWeatherLiveData = MutableLiveData<NetworkRequest<ResponseCurrentWeather>>()
    val currentWeatherLiveData: LiveData<NetworkRequest<ResponseCurrentWeather>> = _currentWeatherLiveData

    //--- api call ---//,
    fun getCurrentWeather(lat: Double, lon: Double) = viewModelScope.launch {
        _currentWeatherLiveData.value = NetworkRequest.Loading()
        val response = repository.getCurrentWeather(lat , lon)
        _currentWeatherLiveData.value = NetworkResponse(response).generateResponse()
    }

    //--- data base ---//
    fun getCities() = viewModelScope.launch {
        repository.getCities().collect {
            _citiesLiveData.value = it
        }
    }

}