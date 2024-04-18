package com.jmzd.ghazal.weatherappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesEntity
import com.jmzd.ghazal.weatherappmvvm.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    //Cities from database
    private val _citiesLiveData = MutableLiveData<List<CitiesEntity>>()
    val citiesLiveData: LiveData<List<CitiesEntity>> = _citiesLiveData

    fun getCities() = viewModelScope.launch {
        repository.getCities().collect {
            _citiesLiveData.value = it
        }
    }

}