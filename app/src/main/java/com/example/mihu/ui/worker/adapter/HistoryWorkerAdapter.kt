package com.example.mihu.ui.worker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mihu.R
import com.example.mihu.data.remote.response.worker.OrdersItem
import com.example.mihu.databinding.ItemHistoryBinding


class HistoryWorkerAdapter :
    ListAdapter<OrdersItem, HistoryWorkerAdapter.HistoryViewHolder>(JobHistory()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OrdersItem) {
            binding.apply {
                tvServiceName.text = data.category
                tvStatus.text = data.status
                tvServicePrice.text = itemView.context.getString(R.string.price_format, data.price)
                binding.ivAvatar.setImageResource(R.drawable.ic_pp_default)
            }
        }
    }

    class JobHistory : DiffUtil.ItemCallback<OrdersItem>() {
        override fun areItemsTheSame(
            oldItem: OrdersItem,
            newItem: OrdersItem
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: OrdersItem,
            newItem: OrdersItem
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
