package com.example.mihu.data.remote.retrofit

import com.example.mihu.data.remote.response.user.CategoriesResponse
import com.example.mihu.data.remote.response.user.LoginResponse
import com.example.mihu.data.remote.response.user.RegisterResponse
import com.example.mihu.data.remote.response.worker.LoginWorkerResponse
import com.example.mihu.data.remote.response.worker.RegisterWorkerResponse
import com.example.mihu.ui.worker.register.RegisterWorkerActivity
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("recruiter/register")
    suspend fun register(
        @Field("username") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("recruiter/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("worker/register")
    suspend fun registerWorker(
        @Field("username") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterWorkerResponse

    @FormUrlEncoded
    @POST("worker/login")
    suspend fun loginWorker(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginWorkerResponse


    @GET("categories")
    suspend fun getCategories(): CategoriesResponse
}