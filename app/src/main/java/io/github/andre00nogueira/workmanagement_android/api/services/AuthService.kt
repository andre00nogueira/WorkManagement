package io.github.andre00nogueira.workmanagement_android.api.services

import io.github.andre00nogueira.workmanagement_android.api.requests.LoginRequest
import io.github.andre00nogueira.workmanagement_android.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    suspend fun authenticate(@Body loginRequest: LoginRequest): Response<AuthResponse>
}