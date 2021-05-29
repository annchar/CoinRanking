package com.annchar.coinranking.ui.cryptolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.annchar.coinranking.R
import com.annchar.coinranking.databinding.ItemCryptoBinding
import com.annchar.coinranking.ui.models.CryptoItemResponse

class CryptoListAdapter(private val cryptoClickListener: (cryptoItem: CryptoItemResponse) -> Unit) :
    PagingDataAdapter<CryptoItemResponse, CryptoListAdapter.ViewHolder>(DiffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemCryptoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_crypto,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ItemCryptoBinding) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        init {
            binding.root.setOnClickListener {
                val cryptoItem = getItem(absoluteAdapterPosition)
                cryptoItem?.let { cryptoClickListener(it) }
            }
        }

        fun bind(item: CryptoItemResponse) {
            with(binding) {
                cryptoItem = item
            }
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<CryptoItemResponse>() {
        override fun areItemsTheSame(oldItem: CryptoItemResponse, newItem: CryptoItemResponse): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CryptoItemResponse, newItem: CryptoItemResponse): Boolean {
            return oldItem == newItem
        }
    }
}