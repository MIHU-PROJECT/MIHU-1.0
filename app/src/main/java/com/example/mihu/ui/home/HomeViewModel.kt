package com.example.mihu.ui.home

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


class HomeViewModel(
    private val mMihuRepository: MihuRepository,
    preferencesManager: UserPreferences
) : ViewModel() {
    val token: LiveData<String> = preferencesManager.getToken().asLiveData()

    fun getSessionData(): LiveData<UserModel> =
        mMihuRepository.getSession().asLiveData()

    fun getCategories() =
        mMihuRepository.getCategories()


    private val _result = MutableLiveData<List<Prediction>>()
    val result: LiveData<List<Prediction>> = _result

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun searchUsers(query: String) {
        if (query.isEmpty()) return
        _isLoading.value = true
        val client = ApiConfig.getApiService().predictCategory(query)
        client.enqueue(object : Callback<PredictionsResponse> {
            override fun onResponse(
                call: Call<PredictionsResponse>,
                response: Response<PredictionsResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _result.value = responseBody.data.predictions
                    }
                }
            }

            override fun onFailure(call: Call<PredictionsResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("MainViewModel", "onFailure: ${t.message}")
            }

        })
    }

}