package com.example.mihu.ui.register

import androidx.lifecycle.ViewModel
import com.example.mihu.data.local.repository.MihuRepository

class RegisterViewModel(private val mMihuRepository: MihuRepository) : ViewModel() {

    fun register(name: String, email: String, password: String) =
        mMihuRepository.registerUser(name, email, password)

    fun login(email: String, password: String) =
        mMihuRepository.loginUser(email, password)
}