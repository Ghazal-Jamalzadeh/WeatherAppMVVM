package com.jmzd.ghazal.weatherappmvvm.data.repository

import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesDao
import javax.inject.Inject

class MainRepository @Inject constructor(private val dao: CitiesDao) {
    //Database
    fun getCities() = dao.loadCities()

}