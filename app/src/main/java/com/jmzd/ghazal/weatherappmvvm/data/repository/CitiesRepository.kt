package com.jmzd.ghazal.weatherappmvvm.data.repository

import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesDao
import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesEntity
import javax.inject.Inject

class CitiesRepository @Inject constructor(private  val dao : CitiesDao) {

    fun getMyCitiesList() = dao.loadCities()
    suspend fun deleteCity(entity: CitiesEntity) = dao.deleteCity(entity)

}