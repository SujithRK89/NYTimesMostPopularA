package com.srk.nytimesmostpopular.ui.mostpopular

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srk.nytimesmostpopular.data.remote.model.MostPopularResult

class MostPopularDetailViewModel @ViewModelInject constructor() : ViewModel() {

    val mostPopular: MutableLiveData<MostPopularResult> = MutableLiveData<MostPopularResult>()
}