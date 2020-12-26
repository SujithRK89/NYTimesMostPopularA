package com.srk.nytimesmostpopular.data.remote

import com.srk.nytimesmostpopular.data.remote.model.MostPopularResponse
import com.srk.nytimesmostpopular.utils.RestConfig
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by sujithrk on 25,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */

interface APIService {

    @GET(RestConfig.RELATIVE_URL_GET_MOST_POPULAR + "{period}" + ".json")
    suspend fun getMostPopular(@Path("period") period: String, @Query("api-key") apiKey: String): Response<MostPopularResponse>

}