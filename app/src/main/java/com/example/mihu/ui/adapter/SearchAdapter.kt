package com.example.mihu.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mihu.data.remote.response.user.SearchItems
import com.example.mihu.databinding.ItemServicesBinding
import com.example.mihu.ui.order.OrderActivity
import com.example.mihu.utils.loadImage


class SearchAdapter :
    ListAdapter<SearchItems, SearchAdapter.JobViewHolder>(SearchPredict()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding =
            ItemServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class JobViewHolder(private val binding: ItemServicesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SearchItems) {
            binding.apply {
                tvItemName.text = data.name
                ivAvatar.loadImage(itemView.context, data.categoriesImage)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, OrderActivity::class.java)
                    intent.putExtra(OrderActivity.ID_KEY, data._id)
                    intent.putExtra(OrderActivity.NAME_KEY, data.name)
                    intent.putExtra(OrderActivity.IMAGE_KEY, data.categoriesImage)

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(binding.tvItemName as View, "name"),
                            Pair(binding.ivAvatar as View, "photo")
                        )

                    itemView.context.startActivity(intent, optionsCompat.toBundle())

                }
            }
        }
    }

    class SearchPredict : DiffUtil.ItemCallback<SearchItems>() {
        override fun areItemsTheSame(
            oldItem: SearchItems,
            newItem: SearchItems
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchItems,
            newItem: SearchItems
        ): Boolean {
            return oldItem._id == newItem._id
        }
    }
}
