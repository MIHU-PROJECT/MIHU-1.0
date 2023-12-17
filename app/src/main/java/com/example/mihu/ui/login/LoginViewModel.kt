package com.example.mihu.ui.login

import androidx.lifecycle.ViewModel
import com.example.mihu.data.local.repository.MihuRepository

class LoginViewModel(
    private val mMihuRepository: MihuRepository
) : ViewModel() {

    fun login(email: String, password: String) =
        mMihuRepository.loginUser(email, password)
}