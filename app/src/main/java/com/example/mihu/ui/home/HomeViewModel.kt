package com.example.mihu.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mihu.data.Result
import com.example.mihu.data.local.repository.MihuRepository
import com.example.mihu.data.model.UserModel
import com.example.mihu.data.remote.response.user.Categories
import kotlinx.coroutines.launch


class HomeViewModel(private val mMihuRepository: MihuRepository) : ViewModel() {

    private val _categories = MutableLiveData<Result<List<Categories>>>()
    val categories: LiveData<Result<List<Categories>>>
        get() = _categories

    fun getSessionData(): LiveData<UserModel> =
        mMihuRepository.getSession().asLiveData()

    fun logout() {
        viewModelScope.launch {
            mMihuRepository.logout()
        }
    }

    fun getCategories() {
        mMihuRepository.getCategories()
    }
}