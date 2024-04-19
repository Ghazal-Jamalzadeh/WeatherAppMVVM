package com.jmzd.ghazal.weatherappmvvm.data.repository

import com.jmzd.ghazal.weatherappmvvm.data.network.ApiServices
import com.jmzd.ghazal.weatherappmvvm.utils.CITIES_LIMIT
import javax.inject.Inject

class AddCityRepository @Inject constructor(private  val api : ApiServices) {

    //API
    suspend fun searchCitiesList( q : String ) = api.searchCitiesList(q , CITIES_LIMIT)

}