package io.github.andre00nogueira.workmanagement_android.preferences

import android.content.Context
import android.content.SharedPreferences
import io.github.andre00nogueira.workmanagement_android.utils.Constants

class AuthPreferences(context: Context) {
    private val sp = context.getSharedPreferences(Constants.AUTH_PREFERENCES, Context.MODE_PRIVATE)

    fun getAuthPreferences(): SharedPreferences = sp
    fun setAuthPreferences(): SharedPreferences.Editor = sp.edit()
}