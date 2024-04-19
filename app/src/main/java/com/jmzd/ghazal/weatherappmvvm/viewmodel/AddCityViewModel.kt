package com.jmzd.ghazal.weatherappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesDao
import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesEntity
import com.jmzd.ghazal.weatherappmvvm.data.model.add_city.ResponseCitiesList
import com.jmzd.ghazal.weatherappmvvm.data.repository.AddCityRepository
import com.jmzd.ghazal.weatherappmvvm.data.repository.MainRepository
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddCityViewModel @Inject constructor(private val repository: AddCityRepository , private val dao : CitiesDao) : ViewModel() {
    //Search Cities
    private val _searchedCitiesListLiveData = MutableLiveData<NetworkRequest<ResponseCitiesList>>()
    val searchedcitiesListLiveData: LiveData<NetworkRequest<ResponseCitiesList>> = _searchedCitiesListLiveData

    //--- api call ---//,
    fun searchCitiesList(search : String) = viewModelScope.launch {
        _searchedCitiesListLiveData.value = NetworkRequest.Loading()
        val response = repository.searchCitiesList(search)
        _searchedCitiesListLiveData.value = NetworkResponse(response).generateResponse()
    }

    //Save city
    fun saveCity(entity: CitiesEntity) = viewModelScope.launch {
        dao.saveCity(entity)
    }

}