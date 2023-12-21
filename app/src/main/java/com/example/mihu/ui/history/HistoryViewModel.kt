package com.example.mihu.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mihu.data.local.pref.UserPreferences
import com.example.mihu.data.local.repository.MihuRepository

class HistoryViewModel(
    private val mMihuRepository: MihuRepository,
    preferencesManager: UserPreferences
) : ViewModel() {
    val token: LiveData<String> = preferencesManager.getToken().asLiveData()

    fun getHistory(token: String) =
        mMihuRepository.getHistory(token)

    fun completeJob(token: String, id: String) =
        mMihuRepository.completeJob(token, id)


}