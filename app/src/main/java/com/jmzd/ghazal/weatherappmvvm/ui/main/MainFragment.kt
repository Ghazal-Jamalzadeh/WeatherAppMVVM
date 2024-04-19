package com.jmzd.ghazal.weatherappmvvm.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jmzd.ghazal.weatherappmvvm.R
import com.jmzd.ghazal.weatherappmvvm.databinding.FragmentMainBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseFragment
import com.jmzd.ghazal.weatherappmvvm.utils.events.EventBus
import com.jmzd.ghazal.weatherappmvvm.utils.events.Events
import com.jmzd.ghazal.weatherappmvvm.utils.onceObserve
import com.jmzd.ghazal.weatherappmvvm.utils.setStatusBarIconsColor
import com.jmzd.ghazal.weatherappmvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    //viewModel
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Change status bar color
        requireActivity().setStatusBarIconsColor(false)

        //Cities list
        viewModel.getCities()

        //observers
        loadCitiesData()

        //Event
        lifecycleScope.launch {
            EventBus.subscribe<Events.OnUpdateWeather> {
//                viewModel.callCurrentWeatherApi(it.lat!!, it.lon!!)
//                viewModel.callForecastApi(it.lat, it.lon)
            }
        }
    }

    private fun loadCitiesData() {
        binding.apply {
            viewModel.citiesLiveData.onceObserve(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    //Visibility
                    emptyLay.isVisible = false
//                    //Call api
//                    viewModel.callCurrentWeatherApi(it[0].lat!!, it[0].lon!!)
//                    viewModel.callForecastApi(it[0].lat!!, it[0].lon!!)
                } else {
                    emptyLay.isVisible = true
                    findNavController().navigate(R.id.action_to_addCityFragment)
                }
            }
        }
    }

}