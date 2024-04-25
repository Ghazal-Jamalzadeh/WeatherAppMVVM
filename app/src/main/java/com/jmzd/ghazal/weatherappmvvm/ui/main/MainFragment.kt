package com.jmzd.ghazal.weatherappmvvm.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.github.matteobattilana.weather.PrecipType
import com.jmzd.ghazal.weatherappmvvm.R
import com.jmzd.ghazal.weatherappmvvm.databinding.FragmentMainBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseFragment
import com.jmzd.ghazal.weatherappmvvm.utils.changeVisibility
import com.jmzd.ghazal.weatherappmvvm.utils.events.EventBus
import com.jmzd.ghazal.weatherappmvvm.utils.events.Events
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.weatherappmvvm.utils.onceObserve
import com.jmzd.ghazal.weatherappmvvm.utils.setStatusBarIconsColor
import com.jmzd.ghazal.weatherappmvvm.utils.showSnackBar
import com.jmzd.ghazal.weatherappmvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    //viewModel
    private val viewModel by viewModels<MainViewModel>()

    private val calendar by lazy { Calendar.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Change status bar color
        requireActivity().setStatusBarIconsColor(false)

        //Cities list
        viewModel.getCities()

        //observers
        loadCitiesData()
        loadCurrentWeatherData()

        //Event
        lifecycleScope.launch {
            EventBus.subscribe<Events.OnUpdateWeather> {
                viewModel.getCurrentWeather(it.lat!!, it.lon!!)
//                viewModel.callForecastApi(it.lat, it.lon)
            }
        }

        //InitViews
        binding.apply {
            menuImg.setOnClickListener { findNavController().navigate(R.id.action_to_CitiesFragment) }
            addImg.setOnClickListener { findNavController().navigate(R.id.action_to_addCityFragment) }
        }
    }

    private fun loadCitiesData() {
        binding.apply {
            viewModel.citiesLiveData.onceObserve(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    //Visibility
                    emptyLay.isVisible = false
//                    //Call api
                    viewModel.getCurrentWeather(it[0].lat!!, it[0].lon!!)
//                    viewModel.callForecastApi(it[0].lat!!, it[0].lon!!)
                } else {
                    emptyLay.isVisible = true
                    findNavController().navigate(R.id.action_to_addCityFragment)
                }
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun loadCurrentWeatherData() {
        binding.apply {
            viewModel.currentWeatherLiveData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        loading.changeVisibility(true, container)
                    }

                    is NetworkRequest.Success -> {
                        loading.changeVisibility(false, container)
                        response.data?.let { data ->
                            //Info fragment
                            showAllTxt.setOnClickListener {
//                                val direction = MainFragmentDirections.actionToInfo(data)
//                                findNavController().navigate(direction)
                            }
                            //Name
                            cityName.text = data.name
                            //Weather
                            data.weather?.let { weather ->
                                if (weather.isNotEmpty()) {
                                    weather[0]?.let {
                                        //Info
                                        infoTxt.text = it.description
                                        //Bg
                                        bgImg.load(
                                            if (isNightNow()) R.drawable.bg_night
                                            else it.icon?.let { icon -> setDynamicallyWallpaper(icon) }
                                        ) {
                                            crossfade(true)
                                            crossfade(100)
                                        }
                                    }
                                }
                            }
                            //Main
                            data.main?.let { main ->
                                tempTxt.text = "${main.temp}${getString(R.string.degreeCelsius)}"
                                TempInfoTxt.text = "${main.tempMin}${getString(R.string.degree)}    " +
                                        "${main.tempMax}${getString(R.string.degree)}"
                            }
                        }
                    }

                    is NetworkRequest.Error -> {
                        loading.changeVisibility(false, container)
                        root.showSnackBar(response.error!!)
                    }
                }
            }
        }
    }


    private fun isNightNow(): Boolean {
        // HOUR_OF_DAY -> 24 h
        //HOUR -> 12 h
        return calendar.get(Calendar.HOUR_OF_DAY) >= 18
    }


    private fun setDynamicallyWallpaper(icon: String): Int {
        return when (icon.dropLast(1)) {
            "01" -> {
                initWeatherView(PrecipType.CLEAR)
                R.drawable.bg_sun
            }

            "02", "03", "04" -> {
                initWeatherView(PrecipType.CLEAR)
                R.drawable.bg_cloud
            }

            "09", "10", "11" -> {
                initWeatherView(PrecipType.RAIN)
                R.drawable.bg_rain
            }

            "13" -> {
                initWeatherView(PrecipType.SNOW)
                R.drawable.bg_snow
            }

            "50" -> {
                initWeatherView(PrecipType.CLEAR)
                R.drawable.bg_haze
            }

            else -> 0
        }
    }

    private fun initWeatherView(type: PrecipType) {
        binding.weatherView.apply {
            setWeatherData(type)
            angle = 20
            emissionRate = 100.0f
        }
    }


}