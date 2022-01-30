package io.github.andre00nogueira.workmanagement_android.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.andre00nogueira.workmanagement_android.api.requests.LoginRequest
import io.github.andre00nogueira.workmanagement_android.extensions.hash
import io.github.andre00nogueira.workmanagement_android.navigation.Routes
import io.github.andre00nogueira.workmanagement_android.repositories.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginState(
    val inProgress: MutableState<Boolean> = mutableStateOf(false),
    val hasError: MutableState<Boolean> = mutableStateOf(false),

    val username: MutableState<String> = mutableStateOf(""),
    val password: MutableState<String> = mutableStateOf("")
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val uiState = LoginState()

    fun onSubmit(navController: NavHostController) = viewModelScope.launch {
        uiState.run {
            inProgress.value = true
            if (username.value.isBlank() || password.value.isBlank()) {
                inProgress.value = false
                return@launch
            }

            val loginRequest =
                LoginRequest(username = username.value, password = password.value.hash())
            val response = authRepository.authenticate(loginRequest)

            hasError.value = response == null || !response.isSuccessful
            response?.let {
                if (response.isSuccessful) {
                    response.body()?.let { authResponse ->
                        authRepository.saveAuthToken(authResponse.token)
                        navController.navigate(Routes.JobListRoute.route)
                    }
                }
            }
            inProgress.value = false
        }
    }
}