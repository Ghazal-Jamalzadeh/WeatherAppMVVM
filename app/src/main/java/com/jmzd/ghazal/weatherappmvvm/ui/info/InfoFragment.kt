package com.jmzd.ghazal.weatherappmvvm.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import com.jmzd.ghazal.weatherappmvvm.R
import com.jmzd.ghazal.weatherappmvvm.databinding.FragmentInfoBinding
import com.jmzd.ghazal.weatherappmvvm.utils.BASE_URL_IMAGE
import com.jmzd.ghazal.weatherappmvvm.utils.PNG_IMAGE
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseBottomSheetFragment
import com.jmzd.ghazal.weatherappmvvm.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : BaseBottomSheetFragment<FragmentInfoBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentInfoBinding
        get() = FragmentInfoBinding::inflate

    //args
    private val args by navArgs<InfoFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init views
        binding.apply {
            //Args
            args.data.let { data ->

                //Weather
                data.weather?.let { weather ->
                    if (weather.isNotEmpty()) {
                        weather[0]?.let {
                            //Info
                            infoTxt.text = it.description
                            //Image
                            val image = "$BASE_URL_IMAGE${it.icon}$PNG_IMAGE"
                            iconImg.loadImage(image)
                        }
                    }
                }
                //Main
                data.main?.let { main ->
                    tempTxt.text = "${main.temp}${getString(R.string.degreeCelsius)}"
                    TempInfoTxt.text =
                        "${main.tempMin}${getString(R.string.degree)}    " + "${main.tempMax}${getString(R.string.degree)}"
                    //Include
                    weatherLay.apply {
                        feelCountTxt.text = "${main.feelsLike}${getString(R.string.degreeCelsius)}"
                        pressureCountTxt.text = "${main.humidity}%"
                    }
                }
                //Wind
                data.wind?.let { wind ->
                    weatherLay.windCountTxt.text = "${wind.speed} ${getString(R.string.km_s)}"
                }

            }
        }
    }


}