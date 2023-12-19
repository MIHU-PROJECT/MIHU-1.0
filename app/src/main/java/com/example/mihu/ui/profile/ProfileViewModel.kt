package com.example.mihu.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mihu.data.local.pref.UserPreferences
import com.example.mihu.data.local.repository.MihuRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val mMihuRepository: MihuRepository,
    preferencesManager: UserPreferences
) : ViewModel() {

    val name: LiveData<String> = preferencesManager.getName().asLiveData()

    val email: LiveData<String> = preferencesManager.getEmail().asLiveData()
    fun logout() {
        viewModelScope.launch {
            mMihuRepository.logout()
        }
    }
}

