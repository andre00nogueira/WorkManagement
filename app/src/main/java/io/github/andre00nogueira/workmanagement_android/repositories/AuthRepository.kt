package io.github.andre00nogueira.workmanagement_android.repositories

import android.content.SharedPreferences
import io.github.andre00nogueira.workmanagement_android.api.requests.LoginRequest
import io.github.andre00nogueira.workmanagement_android.api.services.AuthService
import io.github.andre00nogueira.workmanagement_android.model.AuthResponse
import retrofit2.Response
import java.net.ConnectException
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val sharedPrefences: SharedPreferences,
    private val authService: AuthService
) : BaseRepository(sharedPreferences = sharedPrefences) {

    suspend fun authenticate(loginRequest: LoginRequest): Response<AuthResponse>? {
        return try {
            authService.authenticate(loginRequest)
        } catch (e: ConnectException) {
            null
        }
    }

}