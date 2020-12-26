package com.srk.nytimesmostpopular.utils

/**
 * Created by sujithrk on 25,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */
class RestConfig {
    companion object {

        // Environment Production or Debug
        const val IS_PRODUCTION = false

        // BASE URL
        const val BASE_URL_DEV = "https://api.nytimes.com/svc/"
        const val BASE_URL_PRO = ""

        // API Header key and value
        const val CONTENT_TYPE_KEY = "Content-Type"
        const val CONTENT_TYPE_VALUE = "application/json"

        // Client TimeOut
        const val READ_TIME_OUT: Long = 60
        const val CONNECTION_TIME_OUT: Long = 60

        // Input Params
        const val PERIOD = "7"
        const val API_KEY = "1j38PYmbdd94tq7tTGAOqYMo6sgboQ3m"

        // Relative Url
        const val RELATIVE_URL_GET_MOST_POPULAR = "mostpopular/v2/viewed/"

    }
}