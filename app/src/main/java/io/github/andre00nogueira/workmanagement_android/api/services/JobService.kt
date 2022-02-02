package io.github.andre00nogueira.workmanagement_android.api.services

import io.github.andre00nogueira.workmanagement_android.api.requests.CreateJobRequest
import io.github.andre00nogueira.workmanagement_android.model.Job
import retrofit2.Response
import retrofit2.http.*

interface JobService {

    @GET("jobs")
    suspend fun getJobs(
        @Header("Authorization") authToken: String
    ): Response<List<Job>>

    @GET("job/{id}")
    suspend fun getJob(
        @Header("Authorization") authToken: String,
        @Path("id") id: Long
    ): Response<Job>

    @POST("jobs")
    suspend fun createJob(
        @Header("Authorization") authToken: String,
        @Body createJobRequest: CreateJobRequest
    ): Response<Job>

    @DELETE("jobs/{id}")
    suspend fun deleteJob(
        @Header("Authorization") authToken: String,
        @Path("id") id: Long
    )
}