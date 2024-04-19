package com.jmzd.ghazal.weatherappmvvm.ui.add_city

import android.view.LayoutInflater
import com.jmzd.ghazal.weatherappmvvm.databinding.FragmentAddCityBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCityFragment : BaseBottomSheetFragment<FragmentAddCityBinding>(){

    override val bindingInflater: (inflater: LayoutInflater) -> FragmentAddCityBinding
        get() = FragmentAddCityBinding::inflate


}