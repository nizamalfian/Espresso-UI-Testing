package com.nizamalfian.phonenumberspinner

import android.app.Activity
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.atomic.AtomicReference
import org.hamcrest.CoreMatchers.`is` as coreMatcherIs


@RunWith(AndroidJUnit4::class)
class SpinnerSelectionTest {
    @get:Rule
    val activityRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun iterateSpinnerItems() {
        getActivity(activityRule).resources.getStringArray(R.array.labels_array)
            .forEach { label ->
                onView(withId(R.id.label_spinner))
                    .perform(click())
                onData(coreMatcherIs(label))
                    .perform(click())
                onView(withId(R.id.text_phonelabel))
                    .check(matches(withText(containsString(label))))
            }
    }

    private fun <T : Activity?> getActivity(activityScenarioRule: ActivityScenarioRule<T>): T {
        val activityRef: AtomicReference<T> = AtomicReference()
        activityScenarioRule.scenario.onActivity(activityRef::set)
        return activityRef.get()
    }
}
