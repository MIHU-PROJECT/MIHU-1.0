package com.example.mihu.ui.worker.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mihu.data.local.pref.UserPreferences
import com.example.mihu.data.local.repository.WorkerRepository

class HistoryWorkerViewModel(
    private val mWorkerRepository: WorkerRepository,
    preferencesManager: UserPreferences
) : ViewModel() {
    val token: LiveData<String> = preferencesManager.getToken().asLiveData()

    fun getHistory(token: String) =
        mWorkerRepository.getHistory(token)

}