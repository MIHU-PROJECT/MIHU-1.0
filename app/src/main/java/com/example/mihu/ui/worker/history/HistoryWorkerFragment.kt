package com.example.mihu.ui.worker.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mihu.data.Result
import com.example.mihu.data.remote.response.worker.OrdersItem
import com.example.mihu.databinding.FragmentHistoryWorkerBinding
import com.example.mihu.ui.ViewModelFactory
import com.example.mihu.ui.worker.adapter.HistoryWorkerAdapter


class HistoryWorkerFragment : Fragment() {
    private lateinit var adapter: HistoryWorkerAdapter
    private lateinit var binding: FragmentHistoryWorkerBinding
    private val historyViewModel: HistoryWorkerViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryWorkerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HistoryWorkerAdapter()
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistory.adapter = adapter

        historyViewModel.token.observe(viewLifecycleOwner) { token ->
            loadHistory(token)
        }
    }

    private fun loadHistory(token: String) {
        historyViewModel.getHistory(token).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    val ordersList: List<OrdersItem> = result.data.data.orders
                    adapter.submitList(ordersList)
                }

                is Result.Error -> {
                    showLoading(false)
                    // Handle error if needed
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }
}
