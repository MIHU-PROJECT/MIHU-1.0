package com.example.mihu.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mihu.data.local.repository.MihuRepository
import com.example.mihu.data.model.UserModel


class HomeViewModel(private val mMihuRepository: MihuRepository) : ViewModel() {

    fun getSessionData(): LiveData<UserModel> =
        mMihuRepository.getSession().asLiveData()

    fun getCategories() =
        mMihuRepository.getCategories()

}