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
import com.example.mihu.data.remote.response.user.Prediction
import com.example.mihu.databinding.ItemServicesBinding
import com.example.mihu.ui.order.OrderActivity
import com.example.mihu.utils.loadImage


class SearchAdapter(val activity: Activity) :
    ListAdapter<Prediction, SearchAdapter.SearchViewHolder>(SearchPredict()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            ItemServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SearchViewHolder(private val binding: ItemServicesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Prediction) {
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
                            activity,
                            Pair(binding.tvItemName as View, "name"),
                            Pair(binding.ivAvatar as View, "photo")
                        )

                    itemView.context.startActivity(intent, optionsCompat.toBundle())

                }
            }
        }
    }

    class SearchPredict : DiffUtil.ItemCallback<Prediction>() {
        override fun areItemsTheSame(
            oldItem: Prediction,
            newItem: Prediction
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Prediction,
            newItem: Prediction
        ): Boolean {
            return oldItem._id == newItem._id
        }
    }
}
