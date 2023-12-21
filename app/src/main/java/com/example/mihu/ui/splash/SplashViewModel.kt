package com.example.mihu.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mihu.data.local.pref.UserPreferences
import com.example.mihu.data.local.repository.MihuRepository
import com.example.mihu.data.model.UserModel
import com.example.mihu.data.remote.response.user.Prediction
import com.example.mihu.data.remote.response.user.PredictionsResponse
import com.example.mihu.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplashViewModel(
    private val mMihuRepository: MihuRepository,
) : ViewModel() {

    fun getSessionData(): LiveData<UserModel> =
        mMihuRepository.getSession().asLiveData()

}