package com.jmzd.ghazal.weatherappmvvm.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jmzd.ghazal.weatherappmvvm.R
import com.jmzd.ghazal.weatherappmvvm.data.model.main.ResponseForecast.Data
import com.jmzd.ghazal.weatherappmvvm.databinding.ItemForecastBinding
import com.jmzd.ghazal.weatherappmvvm.utils.BASE_URL_IMAGE
import com.jmzd.ghazal.weatherappmvvm.utils.PNG_IMAGE
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseDiffUtils
import com.jmzd.ghazal.weatherappmvvm.utils.convertToDayName
import com.jmzd.ghazal.weatherappmvvm.utils.loadImage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ForecastAdapter @Inject constructor(@ApplicationContext private val context: Context) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    private var items = emptyList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

    inner class ViewHolder(private val binding: ItemForecastBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Data) {
            binding.apply {
                //Date
                item.dtTxt?.let { date ->
                    val dayName = date.split(" ")[0].convertToDayName()
                    val hour = date.split(" ")[1].dropLast(3)
                    dateTxt.text = "$dayName\n$hour"
                }
                //Image
                val image = "$BASE_URL_IMAGE${item.weather?.get(0)?.icon}$PNG_IMAGE"
                iconImg.loadImage(image)
                //Temp
                item.main?.let { main ->
                    tempTxt.text = "${main.temp}${context.getString(R.string.degree)}"
                    humidityTxt.text = "${main.humidity}%"
                }
            }
        }
    }

    fun setData(data: List<Data>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}