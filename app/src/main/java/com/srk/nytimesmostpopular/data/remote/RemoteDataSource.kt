package com.srk.nytimesmostpopular.data.remote

import javax.inject.Inject

/**
 * Created by sujithrk on 25,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */
class RemoteDataSource @Inject constructor(
    private val apiService: APIService
): BaseDataSource() {

    suspend fun getMostPopular(period: String, apiKey: String) = getResult { apiService.getMostPopular(period, apiKey) }

}