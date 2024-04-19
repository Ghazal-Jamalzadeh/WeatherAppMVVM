package com.jmzd.ghazal.weatherappmvvm.ui.add_city


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jmzd.ghazal.weatherappmvvm.data.model.add_city.ResponseCitiesList.ResponseCitiesListItem
import com.jmzd.ghazal.weatherappmvvm.databinding.ItemCitiesBinding
import com.jmzd.ghazal.weatherappmvvm.utils.base.BaseDiffUtils
import javax.inject.Inject

class CitiesAdapter @Inject constructor() : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    private var items = emptyList<ResponseCitiesListItem>()

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
        fun bind(item: ResponseCitiesListItem) {
            binding.apply {
                //Name
                if (item.localNames?.fa != null)
                    citiesNameTxt.text = "${item.localNames.fa} - ${item.country}"
                else
                    citiesNameTxt.text = "${item.name} - ${item.country}"
                //Click
                root.setOnClickListener {
                    //Click
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((ResponseCitiesListItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (ResponseCitiesListItem) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<ResponseCitiesListItem>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }
}