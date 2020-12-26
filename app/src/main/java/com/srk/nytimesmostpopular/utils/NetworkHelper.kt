package com.srk.nytimesmostpopular.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sujithrk on 25,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */
@Singleton
class NetworkHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(
                networkCapabilities) ?: return false

        return when {
            activeNetwork.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(
                NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}