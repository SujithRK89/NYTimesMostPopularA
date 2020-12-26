package com.srk.nytimesmostpopular.handler

import com.srk.nytimesmostpopular.data.remote.model.MostPopularResult

/**
 * Created by sujithrk on 25,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */
interface MostPopularHandler {
    fun onItemClick(mostPopularResult: MostPopularResult)
}