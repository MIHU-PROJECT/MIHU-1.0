package com.example.mihu.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mihu.data.Result
import com.example.mihu.data.remote.response.user.OrdersItem
import com.example.mihu.databinding.FragmentHistoryBinding
import com.example.mihu.ui.ViewModelFactory
import com.example.mihu.ui.adapter.HistoryAdapter


class HistoryFragment : Fragment() {
    private lateinit var adapter: HistoryAdapter
    private lateinit var binding: FragmentHistoryBinding
    private val historyViewModel: HistoryViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyViewModel.token.observe(viewLifecycleOwner) { token ->

            adapter = HistoryAdapter()
            loadHistory (token)
        }

    }

    private fun loadHistory(token: String) {
        historyViewModel.getHistory(token).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    val layoutManager = LinearLayoutManager(requireContext())
                    val ordersList: List<OrdersItem> = result.data.data.orders
                    adapter.submitList(ordersList)
                    binding.rvHistory.layoutManager = layoutManager
                    binding.rvHistory.adapter = adapter

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
