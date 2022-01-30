package io.github.andre00nogueira.workmanagement_android.preferences

import android.content.Context
import io.github.andre00nogueira.workmanagement_android.utils.Constants

class AuthPreferences(context: Context) {
    private val sp = context.getSharedPreferences(Constants.AUTH_PREFERENCES, Context.MODE_PRIVATE)

    fun saveAuthToken(authToken: String) =
        sp.edit().putString(Constants.AUTH_TOKEN, authToken).apply()

    fun getAuthToken(): String = sp.getString(Constants.AUTH_TOKEN, "").orEmpty()
}