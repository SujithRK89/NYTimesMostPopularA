package com.srk.nytimesmostpopular.data.remote.model


import com.google.gson.annotations.SerializedName

/**
 * Created by sujithrk on 25,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */

data class MostPopularResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<MostPopularResult>,
    @SerializedName("status")
    val status: String
)