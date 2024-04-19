package com.jmzd.ghazal.weatherappmvvm.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesEntity
import com.jmzd.ghazal.weatherappmvvm.databinding.FragmentCitiesBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseBottomSheetFragment
import com.jmzd.ghazal.weatherappmvvm.utils.events.EventBus
import com.jmzd.ghazal.weatherappmvvm.utils.events.Events
import com.jmzd.ghazal.weatherappmvvm.utils.other.CityClickTypes
import com.jmzd.ghazal.weatherappmvvm.utils.setupRecyclerview
import com.jmzd.ghazal.weatherappmvvm.viewmodel.AddCityViewModel
import com.jmzd.ghazal.weatherappmvvm.viewmodel.CitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CitiesFragment : BaseBottomSheetFragment<FragmentCitiesBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentCitiesBinding
        get() = FragmentCitiesBinding::inflate

    //viewModel
    private val viewModel by viewModels<CitiesViewModel>()

    //adapter
    @Inject
    lateinit var citiesAdapter: CitiesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Cities list
        viewModel.getMyCitiesList()
        //observers
        loadCitiesData()
    }

    private fun loadCitiesData() {
        binding.apply {
            viewModel.myCitiesLiveData.observe(viewLifecycleOwner) { cities ->
                //Visibility
                visibilityView(cities.isEmpty())
                //Fill recyclerview
                if (cities.isNotEmpty()) {
                    initRecyclerView(cities)
                }
            }
        }
    }

    private fun initRecyclerView(cities: List<CitiesEntity>) {
        citiesAdapter.setData(cities)
        binding.citiesList.setupRecyclerview(LinearLayoutManager(requireContext()), citiesAdapter)
        //Click
        citiesAdapter.setOnItemClickListener { data, type ->
            if (type == CityClickTypes.SELECT) {
                //Update event
                lifecycleScope.launch {
                    EventBus.publish(Events.OnUpdateWeather(data.name, data.lat, data.lon))
                }
                //Close dialog
                this@CitiesFragment.dismiss()
            } else {
                viewModel.deleteCity(data)
            }
        }
    }

    private fun visibilityView(isEmpty: Boolean) {
        binding.apply {
            emptyLay.isVisible = isEmpty
            containerGroup.isVisible = isEmpty.not()
        }
    }
}