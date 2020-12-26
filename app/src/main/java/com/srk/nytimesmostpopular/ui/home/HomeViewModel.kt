package com.srk.nytimesmostpopular.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srk.nytimesmostpopular.R
import com.srk.nytimesmostpopular.data.remote.model.MostPopularResult
import com.srk.nytimesmostpopular.data.remote.repository.Repository
import com.srk.nytimesmostpopular.utils.NetworkHelper
import com.srk.nytimesmostpopular.utils.Resource
import com.srk.nytimesmostpopular.utils.RestConfig.Companion.API_KEY
import com.srk.nytimesmostpopular.utils.RestConfig.Companion.PERIOD
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel @ViewModelInject constructor(private val repository: Repository, private val networkHelper: NetworkHelper) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isLoading: LiveData<Boolean> = _isLoading
    private val _mostPopularResult: MutableLiveData<List<MostPopularResult>> = MutableLiveData<List<MostPopularResult>>()
    val mostPopularResult: LiveData<List<MostPopularResult>> = _mostPopularResult
    private val _errorMessage: MutableLiveData<String> = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    private val mostPopularResponse = repository.getMostPopular(PERIOD, API_KEY)

    init {
        getMostPopular()
    }

    fun getMostPopular() = viewModelScope.launch {

        if (networkHelper.isNetworkConnected()) {
            mostPopularResponse.observeForever {

                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        _isLoading.postValue(false)
                        if (it.data != null) {
                            val _mostPopularResponse = it.data
                            if (_mostPopularResponse.status == "OK") {
                                Timber.e("User Value===>> %s", _mostPopularResponse)
                                _mostPopularResult.postValue(_mostPopularResponse.results)
                            }
                        }
                    }
                    Resource.Status.ERROR -> {
                        _isLoading.postValue(false)
                        _errorMessage.postValue(it.message)
                        Timber.e("Error===>> %s", it.message)
                    }
                    Resource.Status.LOADING -> {
                        _isLoading.postValue(true)
                        Timber.e("Inside====>> Loading")
                    }
                }
            }
        }
        else
            _errorMessage.postValue("No internet connection, Try again")
    }

    override fun onCleared() {
        super.onCleared()
    }
}