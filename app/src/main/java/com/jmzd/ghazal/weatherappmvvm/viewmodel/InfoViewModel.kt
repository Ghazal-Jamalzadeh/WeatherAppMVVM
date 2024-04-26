package com.jmzd.ghazal.weatherappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmzd.ghazal.weatherappmvvm.data.model.info.ResponsePollution
import com.jmzd.ghazal.weatherappmvvm.data.repository.InfoRepository
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InfoViewModel @Inject constructor(private val repository: InfoRepository) : ViewModel() {
    //Search Cities
    private val _pollutionLiveData = MutableLiveData<NetworkRequest<ResponsePollution>>()
    val pollutionLiveData: LiveData<NetworkRequest<ResponsePollution>> = _pollutionLiveData

    //--- api call ---//,
    fun getPollution(lat : Double , lon : Double) = viewModelScope.launch {
        _pollutionLiveData.value = NetworkRequest.Loading()
        val response = repository.getPollution(lat , lon)
        _pollutionLiveData.value = NetworkResponse(response).generateResponse()
    }


}