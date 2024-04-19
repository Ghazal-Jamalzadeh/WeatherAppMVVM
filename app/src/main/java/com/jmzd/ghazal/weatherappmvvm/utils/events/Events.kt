package com.jmzd.ghazal.weatherappmvvm.utils.events

class Events {
    data class OnUpdateWeather(val name: String?, val lat: Double?, val lon: Double?)
}