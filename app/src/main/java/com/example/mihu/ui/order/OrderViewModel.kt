package com.example.mihu.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mihu.data.local.pref.UserPreferences
import com.example.mihu.data.local.repository.MihuRepository

class OrderViewModel(
    private val mMihuRepository: MihuRepository,
    preferencesManager: UserPreferences
) : ViewModel() {
    val token: LiveData<String> = preferencesManager.getToken().asLiveData()
    fun postOrder(
        token: String,
        name: String,
        description: String,
        categoryId: String,
        price: Number,
    ) =
        mMihuRepository.postOrder(token, name, description, categoryId, price)
}