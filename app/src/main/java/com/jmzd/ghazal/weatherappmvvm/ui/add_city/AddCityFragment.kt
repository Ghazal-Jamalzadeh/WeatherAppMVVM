package com.jmzd.ghazal.weatherappmvvm.ui.add_city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmzd.ghazal.weatherappmvvm.R
import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesEntity
import com.jmzd.ghazal.weatherappmvvm.data.model.add_city.ResponseCitiesList.ResponseCitiesListItem
import com.jmzd.ghazal.weatherappmvvm.databinding.FragmentAddCityBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseBottomSheetFragment
import com.jmzd.ghazal.weatherappmvvm.utils.events.EventBus
import com.jmzd.ghazal.weatherappmvvm.utils.events.Events
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.weatherappmvvm.utils.setupRecyclerview
import com.jmzd.ghazal.weatherappmvvm.utils.showSnackBar
import com.jmzd.ghazal.weatherappmvvm.viewmodel.AddCityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddCityFragment : BaseBottomSheetFragment<FragmentAddCityBinding>(){

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentAddCityBinding
        get() = FragmentAddCityBinding::inflate

    //viewModel
    private val viewModel by viewModels<AddCityViewModel>()

    //adapter
    @Inject
    lateinit var citiesAdapter : CitiesAdapter

    //other
    @Inject
    lateinit var cityEntity : CitiesEntity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //InitViews
        binding.apply {
            searchInpLay.setEndIconOnClickListener {
                val search = searchEdt.text.toString()
                if (isNetworkAvailable) {
                    if (search.isNotEmpty())
                        viewModel.searchCitiesList(search)
                    else
                        root.showSnackBar(getString(R.string.searchCanNotBeEmpty))
                }
            }
        }

        //observers
        loadSearchCityData()
    }

    private fun loadSearchCityData() {
        binding.apply {
            viewModel.searchedcitiesListLiveData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        loading.isVisible = true
                    }

                    is NetworkRequest.Success -> {
                        loading.isVisible = false
                        response.data?.let { data ->
                            if (data.isNotEmpty()) {
                                initRecyclerView(data)
                            }
                        }
                    }

                    is NetworkRequest.Error -> {
                        loading.isVisible = false
                        root.showSnackBar(response.error!!)
                    }
                }
            }
        }
    }

    private fun initRecyclerView(list: List<ResponseCitiesListItem>) {
        citiesAdapter.setData(list)
        binding.citiesList.setupRecyclerview(LinearLayoutManager(requireContext()), citiesAdapter)
        //Click
        citiesAdapter.setOnItemClickListener {
            //Set data into entity
            cityEntity.lat = it.lat
            cityEntity.lon = it.lon
            if (it.localNames?.fa != null)
                cityEntity.name = it.localNames.fa
            else
                cityEntity.name = it.name
            //Save city
            viewModel.saveCity(cityEntity)
            //Update event
            lifecycleScope.launch {
                EventBus.publish(Events.OnUpdateWeather(cityEntity.name, it.lat, it.lon))
            }
            //Close dialog
            this@AddCityFragment.dismiss()
        }
    }

}