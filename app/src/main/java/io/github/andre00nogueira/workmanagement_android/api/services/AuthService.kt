package io.github.andre00nogueira.workmanagement_android.api.services

import io.github.andre00nogueira.workmanagement_android.api.requests.LoginRequest
import io.github.andre00nogueira.workmanagement_android.api.requests.RegisterRequest
import io.github.andre00nogueira.workmanagement_android.model.AuthResponse
import io.github.andre00nogueira.workmanagement_android.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    suspend fun authenticate(
        @Body loginRequest: LoginRequest
    ): Response<AuthResponse>

    @POST("auth/register")
    suspend fun registerAccount(@Body registerRequest: RegisterRequest): Response<User>
}