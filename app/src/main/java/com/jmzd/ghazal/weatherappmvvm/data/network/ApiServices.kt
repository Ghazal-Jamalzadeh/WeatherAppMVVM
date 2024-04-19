package com.jmzd.ghazal.weatherappmvvm.data.network

import com.jmzd.ghazal.weatherappmvvm.data.model.add_city.ResponseCitiesList
import com.jmzd.ghazal.weatherappmvvm.data.model.main.ResponseCurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("geo/1.0/direct")
    suspend fun searchCitiesList(@Query("q") q: String, @Query("limit") limit: Int): Response<ResponseCitiesList>

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double, @Query("lon") lon: Double, @Query("lang") lang: String, @Query("units") units: String,
    ): Response<ResponseCurrentWeather>
}