package com.srk.nytimesmostpopular.app

import android.app.Application
import android.content.Context
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by sujithrk on 25,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */
@HiltAndroidApp
class NYTimesApplication: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}