package com.jmzd.ghazal.weatherappmvvm.data.repository

import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesDao
import com.jmzd.ghazal.weatherappmvvm.data.network.ApiServices
import javax.inject.Inject

class MainRepository @Inject constructor(private val dao: CitiesDao) {
    //Database
    fun getCities() = dao.loadCities()

}