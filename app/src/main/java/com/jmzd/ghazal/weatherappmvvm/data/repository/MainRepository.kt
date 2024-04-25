package com.jmzd.ghazal.weatherappmvvm.data.repository

import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesDao
import com.jmzd.ghazal.weatherappmvvm.data.network.ApiServices
import com.jmzd.ghazal.weatherappmvvm.utils.FA
import com.jmzd.ghazal.weatherappmvvm.utils.METRIC
import javax.inject.Inject

class MainRepository @Inject constructor(private val dao: CitiesDao , private val api : ApiServices) {
    //Database
    fun getCities() = dao.loadCities()

    //Api
    suspend fun getCurrentWeather(lat: Double, lon: Double) = api.getCurrentWeather(lat, lon, FA, METRIC)
    suspend fun getForecast(lat: Double, lon: Double) = api.getForecast(lat, lon, FA, METRIC)

}