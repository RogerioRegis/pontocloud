package com.example.pontocloud.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pontocloud.databinding.ItemPontoBinding
import com.example.pontocloud.model.PontoRecord
import java.text.SimpleDateFormat
import java.util.*

class PontoAdapter : ListAdapter<PontoRecord, PontoAdapter.PontoViewHolder>(PontoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PontoViewHolder {
        val binding = ItemPontoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PontoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PontoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PontoViewHolder(private val binding: ItemPontoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(record: PontoRecord) {
            val dateSdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val timeSdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            val date = Date(record.timestamp)

            binding.tvItemDate.text = dateSdf.format(date)
            binding.tvItemTime.text = timeSdf.format(date)
            binding.tvItemStatus.text = if (record.isSynced) "Sincronizado" else "Pendente"
            binding.tvItemLocation.text = "Lat: ${record.latitude}, Lon: ${record.longitude}"
        }
    }

    class PontoDiffCallback : DiffUtil.ItemCallback<PontoRecord>() {
        override fun areItemsTheSame(oldItem: PontoRecord, newItem: PontoRecord): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: PontoRecord, newItem: PontoRecord): Boolean = oldItem == newItem
    }
}
