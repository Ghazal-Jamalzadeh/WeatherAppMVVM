package com.jmzd.ghazal.weatherappmvvm.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jmzd.ghazal.weatherappmvvm.R
import com.jmzd.ghazal.weatherappmvvm.databinding.FragmentMainBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseFragment
import com.jmzd.ghazal.weatherappmvvm.utils.setStatusBarIconsColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override val bindingInflater: (inflater: LayoutInflater) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Change status bar color
        requireActivity().setStatusBarIconsColor(false)
    }

}