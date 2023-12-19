package com.example.mihu.data.local.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.mihu.data.Result
import com.example.mihu.data.local.pref.UserPreferences
import com.example.mihu.data.model.UserModel
import com.example.mihu.data.remote.response.user.CategoriesResponse
import com.example.mihu.data.remote.response.user.HistoryResponse
import com.example.mihu.data.remote.response.user.LoginResponse
import com.example.mihu.data.remote.retrofit.ApiService

class MihuRepository(
    private val apiService: ApiService,
    private val pref: UserPreferences,
) {

    fun getCategories(
    ): LiveData<Result<CategoriesResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getCategories()
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

    fun getSession() = pref.getSession()

    suspend fun logout() {
        pref.logout()
    }

    fun getHistory(
        token: String
    ): LiveData<Result<HistoryResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.getHistory(token)
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

    fun postOrder(
        token: String,
        name: String,
        description: String,
        categoryId: String,
        price: Number,
    ): LiveData<Result<String>> =
        liveData {
            emit(Result.Loading)
            try {
                val response =
                    apiService.postOrder(token, name, description, categoryId, price)
                if (response.error) {
                    emit(Result.Error("Post Order Error: ${response.message}"))
                    Log.d("Post Order Error", response.message)
                } else {
                    emit(Result.Success("User Created"))
                    Log.d("Post Order Success", response.message)
                }
            } catch (e: Exception) {
                emit(Result.Error("Error : ${e.message.toString()}"))
                Log.d("Post Order Exception", e.message.toString())
            }
        }

    fun registerUser(
        name: String,
        email: String,
        password: String
    ): LiveData<Result<String>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.register(name, email, password)
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

    fun loginUser(
        email: String,
        password: String
    ): LiveData<Result<LoginResponse>> =
        liveData {

            emit(Result.Loading)
            try {
                val response = apiService.login(email, password)
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
        private var instance: MihuRepository? = null
        fun getInstance(
            apiService: ApiService,
            pref: UserPreferences,
        ): MihuRepository =
            instance ?: synchronized(this) {
                instance ?: MihuRepository(apiService, pref)
            }.also { instance = it }
    }

}