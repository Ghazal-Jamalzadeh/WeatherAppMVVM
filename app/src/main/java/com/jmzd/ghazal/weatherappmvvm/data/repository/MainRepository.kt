package com.jmzd.ghazal.weatherappmvvm.data.repository

import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesDao
import com.jmzd.ghazal.weatherappmvvm.data.network.ApiServices
import javax.inject.Inject

class MainRepository @Inject constructor(private val dao: CitiesDao , private  val api : ApiServices) {
    //Database
    fun getCities() = dao.loadCities()

    //API
    suspend fun searchCities( q : String , limit : Int) = api.searchCities(q , limit)

}