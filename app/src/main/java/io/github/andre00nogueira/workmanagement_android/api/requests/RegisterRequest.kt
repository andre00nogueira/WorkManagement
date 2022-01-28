package io.github.andre00nogueira.workmanagement_android.api.requests

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val name: String
)