package com.example.mihu.ui.worker.login

import androidx.lifecycle.ViewModel
import com.example.mihu.data.local.repository.WorkerRepository

class LoginWorkerViewModel(
    private val mWorkerRepository: WorkerRepository
) : ViewModel() {

    fun login(email: String, password: String) =
        mWorkerRepository.loginWorker(email, password)
}