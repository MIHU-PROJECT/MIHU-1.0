package com.example.mihu.ui.worker.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mihu.data.Result
import com.example.mihu.data.remote.response.worker.Job
import com.example.mihu.databinding.FragmentTaskBinding
import com.example.mihu.ui.ViewModelFactory
import com.example.mihu.ui.worker.adapter.JobAdapter


class TaskFragment : Fragment() {
    private lateinit var adapter: JobAdapter
    private lateinit var binding: FragmentTaskBinding
    private val taskViewModel: TaskViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskViewModel.token.observe(viewLifecycleOwner) { token ->

            adapter = JobAdapter()
            loadHistory(token)
        }

    }

    private fun loadHistory(token: String) {
        taskViewModel.getOrder(token).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    val layoutManager = LinearLayoutManager(requireContext())
                    val ordersList: List<Job> = result.data.data.jobs
                    adapter.submitList(ordersList)
                    binding.rvGithub.layoutManager = layoutManager
                    binding.rvGithub.adapter = adapter

                }

                is Result.Error -> {
                    showLoading(false)
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }
}
