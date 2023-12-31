package com.example.mihu.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mihu.R
import com.example.mihu.databinding.FragmentProfileBinding
import com.example.mihu.ui.ViewModelFactory
import com.example.mihu.ui.login.LoginActivity
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views and set up any additional logic here
        setupProfileInfo()
        setupClickListeners()
    }

    private fun setupProfileInfo() {
        binding.ivPhoto.setImageResource(R.drawable.ic_pp_default)
        viewModel.email.observe(viewLifecycleOwner) {
            binding.tvEmail.text = it
        }

        viewModel.name.observe(viewLifecycleOwner) {
            binding.tvName.text = it
        }
    }

    private fun setupClickListeners() {
        // Set up click listeners for profile actions (e.g., log out)
        binding.tvLogOut.setOnClickListener {
            lifecycleScope.launch {
                viewModel.logout()
            }
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

        // Add click listeners for other actions if needed
        // ...
    }
}
