package com.example.mihu.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.example.mihu.data.model.JobModel
import com.example.mihu.databinding.ItemServicesBinding
import com.example.mihu.ui.order.OrderFragment
import com.example.mihu.utils.loadImage

class JobAdapter(
    private val dataset: List<JobModel>,
) : RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    inner class JobViewHolder(
        val binding: ItemServicesBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemServicesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return JobViewHolder(binding)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val categories = dataset[position]

        holder.binding.apply {
            tvItemName.text = categories.name
            ivAvatar.loadImage(holder.itemView.context, categories.categoriesImage)
        }

        holder.apply {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, OrderFragment::class.java)
                intent.putExtra(OrderFragment.ID_KEY, categories.id)

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