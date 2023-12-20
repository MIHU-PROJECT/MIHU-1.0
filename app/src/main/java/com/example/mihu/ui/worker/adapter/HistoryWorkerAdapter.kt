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
                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, com.example.mihu.ui.worker.task.com.example.mihu.ui.worker.history.HistoryFragment::class.java)
//                    intent.putExtra(com.example.mihu.ui.worker.task.com.example.mihu.ui.worker.history.HistoryFragment.category, data.name)
//                    intent.putExtra(com.example.mihu.ui.worker.task.com.example.mihu.ui.worker.history.HistoryFragment.status, data.status)
//                    intent.putExtra(com.example.mihu.ui.worker.task.com.example.mihu.ui.worker.history.HistoryFragment.price, data.price)
//
//                    val optionsCompat: ActivityOptionsCompat =
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(
//                            itemView.context as Activity,
//                            Pair(binding.tvServiceName as View, "category"),
//                            Pair(binding.tvStatus as View, "status"),
//                            Pair(binding.tvServicePrice as View, "price")
//                        )
//
//                    itemView.context.startActivity(intent, optionsCompat.toBundle())

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
}
