package com.jmzd.ghazal.weatherappmvvm.ui.cities

import android.view.LayoutInflater
import com.jmzd.ghazal.weatherappmvvm.databinding.FragmentCitiesBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitiesFragment : BaseBottomSheetFragment<FragmentCitiesBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentCitiesBinding
        get() = FragmentCitiesBinding::inflate
}