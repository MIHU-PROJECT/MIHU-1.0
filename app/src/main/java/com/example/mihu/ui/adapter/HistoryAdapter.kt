package com.example.mihu.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mihu.R
import com.example.mihu.data.remote.response.user.OrdersItem
import com.example.mihu.databinding.ItemWorkerProggresBinding


class HistoryAdapter(private val clickListener: HistoryItemClickListener) :
    ListAdapter<OrdersItem, HistoryAdapter.HistoryViewHolder>(JobHistory()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding =
            ItemWorkerProggresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HistoryViewHolder(
        private val binding: ItemWorkerProggresBinding,
        private val clickListener: HistoryItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: OrdersItem) {
            binding.apply {
                tvCategory.text = data.category
                tvStatus2.text = data.status
                ibCheck.visibility =
                    if (data.status == "waiting" || data.status == "completed") View.GONE else View.VISIBLE
                tvSalary.text = itemView.context.getString(R.string.price_format, data.price)
                binding.ivAvatar.setImageResource(R.drawable.ic_pp_default)
                binding.ibCheck.setOnClickListener {
                    clickListener.onCheckButtonClick(data.orderId)
                }
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

    interface HistoryItemClickListener {
        fun onCheckButtonClick(jobId: String)
    }
}