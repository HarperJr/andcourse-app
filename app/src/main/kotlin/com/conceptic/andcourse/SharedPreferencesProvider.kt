package com.conceptic.andcourse

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit

class SharedPreferencesProvider(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(DEFAULT_PREFERENCES, MODE_PRIVATE)

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String): T? = sharedPreferences.all[key] as T?

    fun put(key: String, value: Any) = sharedPreferences.edit {
        when (value) {
            is Boolean -> putBoolean(key, value)
            is String -> putString(key, value)
            is Float -> putFloat(key, value)
            is Int -> putInt(key, value)
        }
    }

    fun remove(key: String) = sharedPreferences.edit {
        remove(key)
    }

    companion object {
        private const val DEFAULT_PREFERENCES = "default_preferences"
    }
}