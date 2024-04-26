package com.jmzd.ghazal.weatherappmvvm.ui.info

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.jmzd.ghazal.weatherappmvvm.R
import com.jmzd.ghazal.weatherappmvvm.databinding.FragmentInfoBinding
import com.jmzd.ghazal.weatherappmvvm.utils.BASE_URL_IMAGE
import com.jmzd.ghazal.weatherappmvvm.utils.PNG_IMAGE
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseBottomSheetFragment
import com.jmzd.ghazal.weatherappmvvm.utils.changeVisibility
import com.jmzd.ghazal.weatherappmvvm.utils.loadImage
import com.jmzd.ghazal.weatherappmvvm.utils.network.NetworkRequest
import com.jmzd.ghazal.weatherappmvvm.utils.showSnackBar
import com.jmzd.ghazal.weatherappmvvm.viewmodel.InfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InfoFragment : BaseBottomSheetFragment<FragmentInfoBinding>() {

    //binding
    override val bindingInflater: (inflater: LayoutInflater) -> FragmentInfoBinding
        get() = FragmentInfoBinding::inflate

    //viewModel
    private val viewModel by viewModels<InfoViewModel>()

    //args
    private val args by navArgs<InfoFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init views
        binding.apply {
            //Args
            args.data.let { data ->

                //Call api
                lifecycleScope.launch {
                    delay(500)
                    data.coord?.let { coord ->
                        viewModel.getPollution(coord.lat!!, coord.lon!!)
                    }
                }

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

        //observers
        loadPollutionData()
    }

    private fun loadPollutionData() {
        binding.apply {
            viewModel.pollutionLiveData.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkRequest.Loading -> {
                        loading.changeVisibility(true, pollutionCard)
                    }

                    is NetworkRequest.Success -> {
                        loading.changeVisibility(false, pollutionCard)
                        response.data?.let { data ->
                            data.list?.let { list ->
                                if (list.isNotEmpty()) {
                                    list[0].let { myData ->
//                                        pollutionLay.apply {
//                                            //Image
//                                            pollutionIconImg.apply {
//                                                setImageResource(pollutionIcon(myData.main))
//                                                //Tint
//                                                imageTintList = ColorStateList.valueOf(
//                                                    ContextCompat.getColor(requireContext(), pollutionColors(myData.main))
//                                                )
//                                            }
//                                            //Info
//                                            pollutionInfoTxt.apply {
//                                                text = pollutionMessage(myData.main)
//                                                setTextColor(
//                                                    ContextCompat.getColor(requireContext(), pollutionColors(myData.main))
//                                                )
//                                            }
//                                            //Shadow
//                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                                                pollutionCard.apply {
//                                                    outlineAmbientShadowColor =
//                                                        ContextCompat.getColor(requireContext(), pollutionColors(myData.main))
//                                                    outlineSpotShadowColor =
//                                                        ContextCompat.getColor(requireContext(), pollutionColors(myData.main))
//                                                }
//                                            }
//                                            //Pollution
//                                            initRecyclerView(fillPollutionData(myData.components))
//                                        }
                                    }
                                }
                            }
                        }
                    }

                    is NetworkRequest.Error -> {
                        loading.changeVisibility(false, pollutionCard)
                        root.showSnackBar(response.error!!)
                    }
                }
            }
        }
    }


}