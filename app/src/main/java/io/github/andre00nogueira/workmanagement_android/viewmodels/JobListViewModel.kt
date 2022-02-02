package io.github.andre00nogueira.workmanagement_android.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.andre00nogueira.workmanagement_android.model.Job
import io.github.andre00nogueira.workmanagement_android.repositories.JobRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

data class JobListState(
    val inProgress: MutableState<Boolean> = mutableStateOf(false),
    val hasError: MutableState<Boolean> = mutableStateOf(false),

    val refresh: MutableState<Long> = mutableStateOf(System.currentTimeMillis()),

    val jobs: MutableState<List<Job>> = mutableStateOf(emptyList())
)

@HiltViewModel
class JobListViewModel @Inject constructor(
    private val jobRepository: JobRepository
) : ViewModel() {

    val uiState = JobListState()

    fun getJobList() = viewModelScope.launch {
        uiState.run {
            inProgress.value = true

            val response = jobRepository.getJobs()

            hasError.value = response == null || !response.isSuccessful
            response?.let {
                if (response.isSuccessful) {
                    response.body()?.let { jobs ->
                        this.jobs.value = jobs
                    }
                }
            }
            inProgress.value = false
        }
    }

    fun deleteJob(job: Job) = viewModelScope.launch {
        uiState.run {
            inProgress.value = true

            jobRepository.deleteJob(job.id)

            inProgress.value = false
            refresh.value = System.currentTimeMillis()
        }
    }
}