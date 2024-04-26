package com.jmzd.ghazal.weatherappmvvm.data.repository

import com.jmzd.ghazal.weatherappmvvm.data.network.ApiServices
import javax.inject.Inject

class InfoRepository @Inject constructor(private val api: ApiServices) {
    suspend fun getPollution(lat: Double, lon: Double) = api.getPollution(lat, lon)
}