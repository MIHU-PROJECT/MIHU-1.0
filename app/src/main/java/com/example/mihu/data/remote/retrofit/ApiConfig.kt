package com.example.mihu.data.remote.retrofit


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    fun getApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://mihu-app-5nx4s4ct3q-et.a.run.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}