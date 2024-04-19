package com.jmzd.ghazal.weatherappmvvm.ui.cities

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.jmzd.ghazal.weatherappmvvm.databinding.FragmentCitiesBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseBottomSheetFragment
import com.jmzd.ghazal.weatherappmvvm.viewmodel.AddCityViewModel
import com.jmzd.ghazal.weatherappmvvm.viewmodel.CitiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitiesFragment : BaseBottomSheetFragment<FragmentCitiesBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentCitiesBinding
        get() = FragmentCitiesBinding::inflate

    //viewModel
    private val viewModel by viewModels<CitiesViewModel>()
}