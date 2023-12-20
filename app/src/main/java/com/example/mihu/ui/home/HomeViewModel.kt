package com.example.mihu.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mihu.data.local.pref.UserPreferences
import com.example.mihu.data.local.repository.MihuRepository
import com.example.mihu.data.model.UserModel


class HomeViewModel(
    private val mMihuRepository: MihuRepository,
    preferencesManager: UserPreferences
) : ViewModel() {
    val token: LiveData<String> = preferencesManager.getToken().asLiveData()

    fun getSessionData(): LiveData<UserModel> =
        mMihuRepository.getSession().asLiveData()

    fun getCategories() =
        mMihuRepository.getCategories()

    fun predictCategory(sentences: String) =
        mMihuRepository.predictCategory(sentences)

}