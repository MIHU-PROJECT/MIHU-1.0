package com.example.mihu.ui.worker.adapter

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
import com.example.mihu.R
import com.example.mihu.data.remote.response.worker.Job
import com.example.mihu.databinding.ItemWorkerBinding
import com.example.mihu.ui.worker.detail.DetailJobWorkerActivity


class JobAdapter :
    ListAdapter<Job, JobAdapter.JobViewHolder>(JobJob()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding =
            ItemWorkerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class JobViewHolder(private val binding: ItemWorkerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Job) {
            binding.apply {
                tvWorkerName.text = data.name
                tvWorkerSalary.text = itemView.context.getString(R.string.price_format, data.price)
                binding.ivAvatar.setImageResource(R.drawable.ic_pp_default)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailJobWorkerActivity::class.java)
                    intent.putExtra(DetailJobWorkerActivity.ID_KEY, data._id)
                    intent.putExtra(DetailJobWorkerActivity.NAME_KEY, data.name)
                    intent.putExtra(DetailJobWorkerActivity.MESSAGE, data.description)
                    intent.putExtra(DetailJobWorkerActivity.PRICE, data.price)

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(binding.tvWorkerName as View, "name"),
                            Pair(binding.tvWorkerSalary as View, "salary"),
                        )

                    itemView.context.startActivity(intent, optionsCompat.toBundle())

                }
            }
        }
    }

    class JobJob : DiffUtil.ItemCallback<Job>() {
        override fun areItemsTheSame(
            oldItem: Job,
            newItem: Job
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Job,
            newItem: Job
        ): Boolean {
            return oldItem._id == newItem._id
        }
    }
}
