package com.nizamalfian.espressoforuitesting

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityInputOutputTest {
    @get:Rule
    val activityRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun activityLaunch() {
        onView(withId(R.id.button_main))
            .perform(click())
        onView(withId(R.id.text_header))
            .check(matches(isDisplayed()))
        onView(withId(R.id.button_second))
            .perform(click())
        onView(withId(R.id.text_header_reply))
            .check(matches(isDisplayed()))
    }

    @Test
    fun inputOutput_success() {
        val text = "This is a test"
        onView(withId(R.id.editText_main))
            .check(matches(isDisplayed()))
            .perform(typeText(text))
        onView(withId(R.id.button_main))
            .check(matches(isDisplayed()))
            .perform(click())
        onView(withId(R.id.text_message))
            .check(matches(isDisplayed()))
            .check(matches(withText(text)))
    }
}
