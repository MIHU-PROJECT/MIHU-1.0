package com.example.mihu.ui.worker.task


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mihu.data.local.pref.UserPreferences
import com.example.mihu.data.local.repository.WorkerRepository
import com.example.mihu.data.model.UserModel

class TaskViewModel(
    private val mWorkerRepository: WorkerRepository,
    preferencesManager: UserPreferences
) : ViewModel() {
    val token: LiveData<String> = preferencesManager.getToken().asLiveData()

    fun getSessionData(): LiveData<UserModel> =
        mWorkerRepository.getSession().asLiveData()

    fun getOrder(token: String) =
        mWorkerRepository.getOrder(token)

}