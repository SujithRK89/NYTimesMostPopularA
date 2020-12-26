package com.srk.nytimesmostpopular.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.srk.nytimesmostpopular.R
import com.srk.nytimesmostpopular.adapter.MostPopularViewHolder
import com.srk.nytimesmostpopular.di.AppModule
import com.srk.nytimesmostpopular.getOrAwaitValue
import com.srk.nytimesmostpopular.launchFragmentInHiltContainer
import com.srk.nytimesmostpopular.ui.MainActivity
import com.srk.nytimesmostpopular.utils.EspressoIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by sujithrk on 26,December,2020
 * Email sujithrk89@gmail.com
 * Copyright (c) 2020 . All rights reserved.
 */
@UninstallModules(AppModule::class)
@HiltAndroidTest
@ExperimentalCoroutinesApi
class HomeFragmentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val LIST_ITEM_IN_TEST = 2

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun test_isHomeFragmentIsVisible_onAppLaunch() {

        onView(withId(R.id.rv_most_popular)).check(matches(isDisplayed()))

    }

    @Test
    fun test_selectedListItem_isDetailFragmentVisible() {

        /*launchFragmentInHiltContainer<HomeFragment> {


        }*/

        onView(withId(R.id.rv_most_popular))
            .perform(actionOnItemAtPosition<MostPopularViewHolder>(LIST_ITEM_IN_TEST, click()))

        onView(withId(R.id.tv_title)).check(matches(withText("Sentence Is Cut for U.S. Student Who Broke Quarantine")))

    }

    @Test
    fun test_backNavigationTo_HomeFragment() {
        onView(withId(R.id.rv_most_popular))
            .perform(actionOnItemAtPosition<MostPopularViewHolder>(LIST_ITEM_IN_TEST, click()))

        pressBack()

        onView(withId(R.id.rv_most_popular)).check(matches(isDisplayed()))
    }


}