package com.jmzd.ghazal.weatherappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesDao
import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesEntity
import com.jmzd.ghazal.weatherappmvvm.data.model.add_city.ResponseCitiesList
import com.jmzd.ghazal.weatherappmvvm.data.repository.AddCityRepository
import com.jmzd.ghazal.weatherappmvvm.data.repository.CitiesRepository
import com.jmzd.ghazal.weatherappmvvm.data.repository.MainRepository
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CitiesViewModel @Inject constructor(private val repository: CitiesRepository,) : ViewModel() {
    //Load my cities
    private val _myCitiesLiveData = MutableLiveData<List<CitiesEntity>>()
    val myCitiesLiveData: LiveData<List<CitiesEntity>> = _myCitiesLiveData

    fun getMyCitiesList() = viewModelScope.launch {
        repository.getMyCitiesList().collect {
            _myCitiesLiveData.value = it
        }
    }

    //Delete
    fun deleteCity(entity: CitiesEntity) = viewModelScope.launch {
        repository.deleteCity(entity)
    }

}