package com.example.mihu.ui.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mihu.R
import com.example.mihu.data.Result
import com.example.mihu.data.remote.response.user.OrdersItem
import com.example.mihu.databinding.FragmentHistoryBinding
import com.example.mihu.ui.ViewModelFactory
import com.example.mihu.ui.adapter.HistoryAdapter
import com.example.mihu.ui.home.HomeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class HistoryFragment : Fragment(), HistoryAdapter.HistoryItemClickListener {

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

        binding = FragmentHistoryBinding.bind(view)
        adapter = HistoryAdapter(this)

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

    override fun onCheckButtonClick(jobId: String) {
        val token = historyViewModel.token.value
        if (token != null) {
            historyViewModel.completeJob(token, jobId)
                .observe(viewLifecycleOwner) { result ->
                    when (result) {
                        is Result.Loading -> {
                            showLoading(true)

                        }

                        is Result.Error -> {
                            showLoading(false)
                            // Handle error
                        }

                        is Result.Success -> {
                            showLoading(false)
                            binding.progressBar.isVisible = false
                            MaterialAlertDialogBuilder(requireContext()).apply {
                                setTitle(getString(R.string.thanks))
                                setMessage(getString(R.string.have_a_nice_day))
                                setPositiveButton("Ok") { dialog, _ ->
                                    dialog.dismiss()
                                    val intent = Intent(
                                        requireContext(),
                                        HomeActivity::class.java
                                    )
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(intent)
                                }
                            }.create().show()
                        }
                    }

                }
        }
    }


    companion object
}
