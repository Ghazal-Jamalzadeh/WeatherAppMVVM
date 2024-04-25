package com.jmzd.ghazal.weatherappmvvm.ui

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import com.jmzd.ghazal.weatherappmvvm.R
import com.jmzd.ghazal.weatherappmvvm.databinding.ActivityMainBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    //Binding
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Transparent status bar
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }

        binding.apply {

        }

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}