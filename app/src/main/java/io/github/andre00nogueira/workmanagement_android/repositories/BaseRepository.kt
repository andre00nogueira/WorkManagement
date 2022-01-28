package io.github.andre00nogueira.workmanagement_android.repositories

import android.content.SharedPreferences
import retrofit2.HttpException
import retrofit2.Response

open class BaseRepository(
    val sharedPreferences: SharedPreferences
) {
    private val authToken = sharedPreferences.getString("AUTH_TOKEN", "")

    suspend fun <T : Any> execute(call: suspend () -> Response<T>): T? {
        val response = call.invoke()
        return try {
            if (response.isSuccessful) {
                val token = response.headers()["Authorization"]
                token?.let {
                    saveAuthToken(it)
                }
            }
            response.body()
        } catch (e: HttpException) {
            null
        }
    }

    private fun saveAuthToken(authToken: String) {
        sharedPreferences.edit().putString("AUTH_TOKEN", authToken).apply()
    }
}