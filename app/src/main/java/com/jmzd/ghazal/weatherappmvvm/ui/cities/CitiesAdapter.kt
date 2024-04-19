package com.jmzd.ghazal.weatherappmvvm.ui.cities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jmzd.ghazal.weatherappmvvm.data.database.CitiesEntity
import com.jmzd.ghazal.weatherappmvvm.databinding.ItemCitiesBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseDiffUtils
import com.jmzd.ghazal.weatherappmvvm.utils.other.CityClickTypes
import javax.inject.Inject

class CitiesAdapter @Inject constructor() : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    private var items = emptyList<CitiesEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCitiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

    inner class ViewHolder(private val binding: ItemCitiesBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: CitiesEntity) {
            binding.apply {
                //Name
                citiesNameTxt.text = item.name
                //Delete
                trashImg.apply {
                    isVisible = true
                    setOnClickListener {
                        onItemClickListener?.let {
                            it(item, CityClickTypes.DELETE)
                        }
                    }
                }
                //Click
                root.setOnClickListener {
                    //Click
                    onItemClickListener?.let {
                        it(item, CityClickTypes.SELECT)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((CitiesEntity, CityClickTypes) -> Unit)? = null

    fun setOnItemClickListener(listener: (CitiesEntity, CityClickTypes) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<CitiesEntity>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}