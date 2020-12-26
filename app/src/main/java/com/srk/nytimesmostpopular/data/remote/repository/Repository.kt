package com.srk.nytimesmostpopular.data.remote.repository

import com.srk.nytimesmostpopular.data.remote.RemoteDataSource
import com.srk.nytimesmostpopular.utils.performGetOperation
import javax.inject.Inject

/**
 * Created by sujithrk on 25,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    fun getMostPopular(period: String, apiKey: String) = performGetOperation(networkCall = {remoteDataSource.getMostPopular(period, apiKey)})

}