package com.conceptic.andcourse.data.api.support

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityHandler(context: Context) {
    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun hasActiveConnection(): Boolean = connectivityManager.activeNetworkInfo.isConnected
}