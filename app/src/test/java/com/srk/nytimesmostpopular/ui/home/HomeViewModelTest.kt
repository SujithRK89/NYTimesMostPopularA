package com.srk.nytimesmostpopular.ui.home

import androidx.lifecycle.LiveData
import com.google.common.truth.Truth.assertThat
import com.srk.nytimesmostpopular.data.remote.repository.Repository
import com.srk.nytimesmostpopular.utils.NetworkHelper
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


/**
 * Created by sujithrk on 26,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */
@ExperimentalCoroutinesApi
@HiltAndroidTest
class HomeViewModelTest {

    @get:Rule()
    var hiltRule = HiltAndroidRule(this)

    private lateinit var isLoadingLiveData: LiveData<Boolean>

    private lateinit var isErrorLiveData: LiveData<String>

    private lateinit var viewModel: HomeViewModel

    @Inject
    private lateinit var repo: Repository

    @Inject
    private lateinit var networkHelper: NetworkHelper

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Before
    fun setUp() {
        viewModel = HomeViewModel(repo, networkHelper)
        viewModel.getMostPopular()


    }

    @Test
    fun testIsLoading() {
        viewModel.getMostPopular()
        isLoadingLiveData = viewModel.isLoading
        assertThat(isLoadingLiveData.value).isEqualTo(true)
    }

    @Test
    fun testGetMostPopularResult() {
        viewModel.getMostPopular()
        assertThat(viewModel.mostPopularResult).isNotNull()
    }

    @Test
    fun testGetErrorMessage() {
        viewModel.getMostPopular()
        assertThat(isErrorLiveData.value).isEqualTo(true)
    }
}