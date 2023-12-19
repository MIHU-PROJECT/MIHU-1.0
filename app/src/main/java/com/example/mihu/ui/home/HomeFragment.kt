package com.example.mihu.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mihu.data.Result
import com.example.mihu.databinding.FragmentHomeBinding
import com.example.mihu.ui.ViewModelFactory
import com.example.mihu.ui.adapter.CategoriesAdapter
import com.example.mihu.ui.login.LoginActivity


class HomeFragment : Fragment() {
    private lateinit var adapter: CategoriesAdapter
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CategoriesAdapter()
        homeViewModel.getSessionData().observe(viewLifecycleOwner) { user ->
            if (!user.isLogin) {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                loadCategories()
            }
        }
    }

    private fun loadCategories() {
        homeViewModel.getCategories().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    val layoutManager = LinearLayoutManager(requireContext())
                    adapter.submitList(result.data.categories)
                    binding.rvAkun.layoutManager = layoutManager
                    binding.rvAkun.adapter = adapter
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
