package com.example.mihu.ui.worker.register

import androidx.lifecycle.ViewModel
import com.example.mihu.data.local.repository.WorkerRepository

class RegisterWorkerViewModel(private val mWorkerRepository: WorkerRepository) : ViewModel() {

    fun register(name: String, email: String, password: String) =
        mWorkerRepository.registerWorker(name, email, password)

    fun login(email: String, password: String) =
        mWorkerRepository.loginWorker(email, password)
}