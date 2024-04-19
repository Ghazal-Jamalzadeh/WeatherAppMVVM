package com.jmzd.ghazal.weatherappmvvm.ui.add_city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.jmzd.ghazal.weatherappmvvm.R
import com.jmzd.ghazal.weatherappmvvm.databinding.FragmentAddCityBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseBottomSheetFragment
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.weatherappmvvm.utils.showSnackBar
import com.jmzd.ghazal.weatherappmvvm.viewmodel.AddCityViewModel
import com.jmzd.ghazal.weatherappmvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCityFragment : BaseBottomSheetFragment<FragmentAddCityBinding>(){

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentAddCityBinding
        get() = FragmentAddCityBinding::inflate

    //viewModel
    private val viewModel by viewModels<AddCityViewModel>()

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
//                                initRecyclerView(data)
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


}