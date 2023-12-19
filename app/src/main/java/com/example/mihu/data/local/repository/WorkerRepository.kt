package com.example.mihu.data.local.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mihu.data.Result
import com.example.mihu.data.local.pref.UserPreferences
import com.example.mihu.data.model.UserModel
import com.example.mihu.data.remote.response.user.HistoryResponse
import com.example.mihu.data.remote.response.worker.JobWorkerResponse
import com.example.mihu.data.remote.response.worker.LoginWorkerResponse
import com.example.mihu.data.remote.retrofit.ApiService

class WorkerRepository(
    private val apiService: ApiService,
    private val pref: UserPreferences,
) {


    fun getSession() = pref.getSession()

    suspend fun logout() {
        pref.logout()
    }

    fun registerWorker(
        name: String,
        email: String,
        password: String
    ): LiveData<Result<String>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.registerWorker(name, email, password)
                if (response.error) {
                    emit(Result.Error("Register Error: ${response.message}"))
                    Log.d("Register Error", response.message)
                } else {
                    emit(Result.Success("User Created"))
                    Log.d("Register Success", response.message)
                }
            } catch (e: Exception) {
                emit(Result.Error("Error : ${e.message.toString()}"))
                Log.d("Register Exception", e.message.toString())
            }
        }

    fun getOrder(
        token: String
    ): LiveData<Result<JobWorkerResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getOrder(token)
                if (response.error) {
                    emit(Result.Error("Categories Error: ${response.message}"))
                    Log.d("Categories Error", response.message)
                } else {
                    emit(Result.Success(response))
                    Log.d("Categories Success", response.message)
                }
            } catch (e: Exception) {
                emit(Result.Error("Error : ${e.message.toString()}"))
                Log.d("Categories Exception", e.message.toString())
            }
        }

    fun loginWorker(
        email: String,
        password: String
    ): LiveData<Result<LoginWorkerResponse>> =
        liveData {

            emit(Result.Loading)
            try {
                val response = apiService.loginWorker(email, password)
                if (response.error) {
                    Log.d("Login Error", response.message)
                    emit(Result.Error("Login Error: ${response.message}"))
                } else {
                    Log.d("Login Success", response.message)
                    emit(Result.Success(response))

                    pref.saveSession(
                        UserModel(
                            response.data.userId,
                            response.data.username,
                            response.data.email,
                            response.data.accessToken,
                            true
                        )
                    )
                }
            } catch (e: Exception) {
                Log.d("Login Exception", e.message.toString())
                emit(Result.Error("Error : ${e.message.toString()}"))
            }

        }

    companion object {
        private var instance: WorkerRepository? = null
        fun getInstance(
            apiService: ApiService,
            pref: UserPreferences,
        ): WorkerRepository =
            instance ?: synchronized(this) {
                instance ?: WorkerRepository(apiService, pref)
            }.also { instance = it }
    }

}