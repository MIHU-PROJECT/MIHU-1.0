package com.example.mihu.data.remote.retrofit

import com.example.mihu.data.remote.response.user.CategoriesResponse
import com.example.mihu.data.remote.response.user.CompleteJobResponse
import com.example.mihu.data.remote.response.user.HistoryResponse
import com.example.mihu.data.remote.response.user.JobResponse
import com.example.mihu.data.remote.response.user.LoginResponse
import com.example.mihu.data.remote.response.user.PredictionsResponse
import com.example.mihu.data.remote.response.user.RegisterResponse
import com.example.mihu.data.remote.response.worker.HistoryWorkerResponse
import com.example.mihu.data.remote.response.worker.JobWorkerResponse
import com.example.mihu.data.remote.response.worker.LoginWorkerResponse
import com.example.mihu.data.remote.response.worker.RegisterWorkerResponse
import com.example.mihu.data.remote.response.worker.TakeJobResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    @FormUrlEncoded
    @POST("recruiter/job")
    suspend fun postOrder(
        @Header("Authorization") token: String? = null,
        @Field("name") name: String,
        @Field("description") description: String,
        @Field("categoryId") categoryId: String,
        @Field("price") price: Number

    ): JobResponse

    @GET("worker/job")
    suspend fun getOrder(
        @Header("Authorization") token: String? = null,
    ): JobWorkerResponse

    @GET("recruiter/order")
    suspend fun getHistory(
        @Header("Authorization") token: String? = null,
    ): HistoryResponse

    @POST("worker/job/{id}")
    suspend fun takeJob(
        @Header("Authorization") token: String? = null,
        @Path("id") jobId: String,

        ): TakeJobResponse

    @PATCH("recruiter/order/{id}")
    suspend fun completeJob(
        @Header("Authorization") token: String? = null,
        @Path("id") jobId: String,
    ): CompleteJobResponse

    @GET("worker/order")
    suspend fun getHistoryWorker(
        @Header("Authorization") token: String? = null,
    ): HistoryWorkerResponse

    @GET("/predict")
    suspend fun predictCategory(
        @Query("sentences") sentences: String
    ): PredictionsResponse

}