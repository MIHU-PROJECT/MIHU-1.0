package com.example.mihu.di

import android.content.Context
import com.example.mihu.data.local.pref.UserPreferences
import com.example.mihu.data.local.pref.dataStore
import com.example.mihu.data.local.repository.MihuRepository
import com.example.mihu.data.local.repository.WorkerRepository
import com.example.mihu.data.remote.retrofit.ApiConfig

object Injection {
    fun providedMihuRepository(context: Context): MihuRepository {
        val apiService = ApiConfig.getApiService()
        val pref = UserPreferences.getInstance(context.dataStore)
        return MihuRepository.getInstance(apiService, pref)
    }
    fun providedWorkerRepository(context: Context): WorkerRepository {
        val apiService = ApiConfig.getApiService()
        val pref = UserPreferences.getInstance(context.dataStore)
        return WorkerRepository.getInstance(apiService, pref)
    }
}