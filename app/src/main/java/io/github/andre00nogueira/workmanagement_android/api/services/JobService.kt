package io.github.andre00nogueira.workmanagement_android.api.services

import io.github.andre00nogueira.workmanagement_android.model.Job
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface JobService {

    @GET("jobs")
    suspend fun getJobs(
        @Header("Authorization") authToken: String
    ): Response<List<Job>>

    @DELETE("jobs/{id}")
    suspend fun deleteJob(
        @Header("Authorization") authToken: String,
        @Path("id") id: Long
    ): Response<Job>
}