package io.github.andre00nogueira.workmanagement_android.repositories

import io.github.andre00nogueira.workmanagement_android.api.requests.LoginRequest
import io.github.andre00nogueira.workmanagement_android.api.requests.RegisterRequest
import io.github.andre00nogueira.workmanagement_android.api.services.AuthService
import io.github.andre00nogueira.workmanagement_android.model.AuthResponse
import io.github.andre00nogueira.workmanagement_android.model.User
import io.github.andre00nogueira.workmanagement_android.preferences.AuthPreferences
import retrofit2.Response
import java.net.ConnectException
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val sharedPreferences: AuthPreferences,
    private val authService: AuthService
) {

    suspend fun authenticate(loginRequest: LoginRequest): Response<AuthResponse>? {
        return try {
            authService.authenticate(loginRequest)
        } catch (e: ConnectException) {
            null
        }
    }

    suspend fun register(registerRequest: RegisterRequest): Response<User>? {
        return try {
            authService.registerAccount(registerRequest)
        } catch (e: ConnectException) {
            null
        }
    }

    fun saveAuthToken(token: String?) {
        token?.let {
            sharedPreferences.saveAuthToken(token)
        }
    }
}