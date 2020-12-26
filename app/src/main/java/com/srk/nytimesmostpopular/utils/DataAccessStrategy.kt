package com.srk.nytimesmostpopular.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers


/**
 * Created by sujithrk on 25,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */
fun <T> performGetOperation(networkCall: suspend () -> Resource<T>):
        LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {

            emit(Resource.loading())

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            val data: MutableLiveData<T> = MutableLiveData<T>()
            data.postValue(responseStatus.data)
            val source = data.map { Resource.success(it) }
            emitSource(source)

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }
    }