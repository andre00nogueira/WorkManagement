package io.github.andre00nogueira.workmanagement_android.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.andre00nogueira.workmanagement_android.api.requests.RegisterRequest
import io.github.andre00nogueira.workmanagement_android.extensions.hash
import io.github.andre00nogueira.workmanagement_android.navigation.Routes
import io.github.andre00nogueira.workmanagement_android.repositories.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterState(
    val inProgress: MutableState<Boolean> = mutableStateOf(false),
    val hasError: MutableState<Boolean> = mutableStateOf(false),

    val username: MutableState<String> = mutableStateOf(""),
    val password: MutableState<String> = mutableStateOf(""),
    val email: MutableState<String> = mutableStateOf(""),
    val name: MutableState<String> = mutableStateOf(""),
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val uiState = RegisterState()

    fun onSubmit(navController: NavHostController) = viewModelScope.launch {
        uiState.run {
            inProgress.value = true
            if (username.value.isBlank() || password.value.isBlank()) {
                inProgress.value = false
                return@launch
            }

            val registerRequest =
                RegisterRequest(
                    username = username.value,
                    password = password.value.hash(),
                    email = email.value,
                    name = name.value,
                )

            val response = authRepository.register(registerRequest)

            hasError.value = response == null || !response.isSuccessful
            response?.let {
                if (response.isSuccessful) {
                    response.body()?.let { authResponse ->
                        navController.navigate(Routes.LoginRoute.route)
                    }
                }
            }
            inProgress.value = false
        }
    }
}