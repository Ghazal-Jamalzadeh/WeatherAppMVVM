package com.jmzd.ghazal.weatherappmvvm.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.jmzd.ghazal.weatherappmvvm.databinding.FragmentInfoBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : BaseBottomSheetFragment<FragmentInfoBinding>() {
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentInfoBinding
        get() = FragmentInfoBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}