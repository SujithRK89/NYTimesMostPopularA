package com.srk.nytimesmostpopular.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.srk.nytimesmostpopular.data.remote.model.MostPopularResult
import com.srk.nytimesmostpopular.data.remote.repository.Repository
import com.srk.nytimesmostpopular.di.AppModule
import com.srk.nytimesmostpopular.getOrAwaitValue
import com.srk.nytimesmostpopular.utils.NetworkHelper
import com.srk.nytimesmostpopular.utils.Resource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
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
@UninstallModules(AppModule::class)
@HiltAndroidTest
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var networkHelper: NetworkHelper

    lateinit var viewModel: HomeViewModel

    private val mostPopularResult = getPopularResult()

    private fun getPopularResult(): MostPopularResult {
        return MostPopularResult(
            "Test",
            "Test",
            1,
            "Test",
            listOf(),
            1,
            listOf(),
            1,
            listOf(),
            "Test",
            listOf(),
            listOf(),
            "Test",
            "Test",
            "Test",
        "Test",
            "Title",
            "Test",
            "Test",
            "Test",
            "Test")
    }

    @Before
    fun init() {
        hiltRule.inject()

        viewModel = HomeViewModel(repository, networkHelper)
    }

    @Test
    fun isLoadingFalseTest() {
        assertThat(viewModel.isLoading.value).isFalse()
    }

    @Test
    fun isLoadingTrueTest() {
        viewModel.setIsLoadingTrue()
        assertThat(viewModel.isLoading.getOrAwaitValue()).isTrue()
    }

    @Test
    fun checkInternetAvailableTrueTest() {
        assertThat(networkHelper.isNetworkConnected()).isTrue()
    }

    @Test
    fun isErrorMessageTrueTest() {
        viewModel.setErrorValue("Error Message")
        assertThat(viewModel.errorMessage.getOrAwaitValue()).contains("Message")
    }

    @Test
    fun isErrorMessageFalseTest() {
        assertThat(viewModel.errorMessage.value).isNull()
    }

    @Test
    fun fakeAPIObjectUpdateTest() {
        viewModel.fakeRequestObject(mostPopularResult)
        assertThat(viewModel.mostPopularResult.value?.get(0)?.title).contains("Title")
    }

    @Test
    fun makeApiCallWorkingTest() {
        assertThat(viewModel.mostPopularResponse.getOrAwaitValue().status).isEqualTo(Resource.Status.LOADING)
    }

}