package io.github.andre00nogueira.workmanagement_android.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.andre00nogueira.workmanagement_android.api.requests.CreateJobRequest
import io.github.andre00nogueira.workmanagement_android.navigation.Routes
import io.github.andre00nogueira.workmanagement_android.repositories.JobRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CreateJobState(
    val inProgress: MutableState<Boolean> = mutableStateOf(false),
    val hasError: MutableState<Boolean> = mutableStateOf(false),

    val jobName: MutableState<String> = mutableStateOf("")
)

@HiltViewModel
class CreateJobViewModel @Inject constructor(
    private val jobRepository: JobRepository
) : ViewModel() {

    val uiState = CreateJobState()

    private fun createJob(navController: NavHostController) = viewModelScope.launch {
        uiState.run {
            inProgress.value = true
            if (jobName.value.isBlank()) {
                inProgress.value = false
                return@launch
            }

            val createJobRequest = CreateJobRequest(jobName.value)
            val response = jobRepository.createJob(createJobRequest)

            hasError.value = response == null || !response.isSuccessful
            inProgress.value = false
            if (!hasError.value) navController.navigate(Routes.JobListRoute.route)
        }
    }

    fun onSubmit(navController: NavHostController) {
        createJob(navController)
    }
}