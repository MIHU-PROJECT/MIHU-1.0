package com.example.mihu.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mihu.data.local.repository.MihuRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val mMihuRepository: MihuRepository) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            mMihuRepository.logout()
        }
    }
}