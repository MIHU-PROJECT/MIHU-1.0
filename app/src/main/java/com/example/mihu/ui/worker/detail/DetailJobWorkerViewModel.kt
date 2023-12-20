package com.example.mihu.ui.worker.detail


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mihu.data.local.pref.UserPreferences
import com.example.mihu.data.local.repository.WorkerRepository

class DetailJobWorkerViewModel(
    private val mWorkerRepository: WorkerRepository,
    preferencesManager: UserPreferences
) : ViewModel() {
    val token: LiveData<String> = preferencesManager.getToken().asLiveData()

    fun takeJob(token: String, id: String) =
        mWorkerRepository.takeJob(token, id)

}