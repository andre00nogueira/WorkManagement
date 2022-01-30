package io.github.andre00nogueira.workmanagement_android.repositories

import io.github.andre00nogueira.workmanagement_android.api.services.JobService
import io.github.andre00nogueira.workmanagement_android.model.Job
import io.github.andre00nogueira.workmanagement_android.preferences.AuthPreferences
import retrofit2.Response
import java.net.ConnectException
import javax.inject.Inject

class JobRepository @Inject constructor(
    private val sharedPreferences: AuthPreferences,
    private val jobService: JobService
) {

    suspend fun getJobs(): Response<List<Job>>? {
        return try {
            jobService.getJobs(getAuthToken())
        } catch (e: ConnectException) {
            null
        }
    }

    suspend fun deleteJob(id: Long): Response<Job>? {
        return try {
            jobService.deleteJob(getAuthToken(), id)
        } catch (e: ConnectException) {
            null
        }
    }

    private fun getAuthToken(): String =
        "Bearer ${sharedPreferences.getAuthToken()}"
}