package com.tasks.dijitalgarajihashingtask.view

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tasks.dijitalgarajihashingtask.R
import kotlinx.coroutines.delay
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HashRetrievingActivityTest
{
    @Before
    fun setUp() {
        val launch = ActivityScenario.launch(HashRetrievingActivity::class.java)

    }
    @Test
    fun test_hash_retrieving_activity_in_view()
    {
        onView(withId(R.id.btnGetHashValue)).check(matches(isDisplayed()))
    }

    @Test
    fun test_clicking_hash_get_button_shows_progress_loading_layout()
    {
        onView(withId(R.id.btnGetHashValue)).perform(click())
        onView(withId(R.id.progressLoading)).check(matches(isDisplayed()))

    }

    @Test
    fun test_clicking_hash_get_button_shows_email_text_view()
    {
        onView(withId(R.id.btnGetHashValue)).perform(click())
        onView(withId(R.id.progressLoading)).check(matches(isDisplayed()))
        onView(withId(R.id.txtEmailResult)).check(matches(isDisplayed()))
        onView(isRoot()).perform(waitFor(2000))

    }
    @Test
    fun test_clicking_hash_get_button_shows_returned_data_and_open_hash_solving_activity_button()
    {

        onView(withId(R.id.btnOpenHashSolvingActivity)).check(matches(not(isDisplayed())))
        onView(withId(R.id.btnGetHashValue)).perform(click())

        onView(isRoot()).perform(waitFor(3000))
        onView(withId(R.id.btnOpenHashSolvingActivity)).check(matches(isDisplayed()))


    }

    @Test
    fun test_clicking_open_hash_solving_button_open_hash_solving_activity()
    {

        onView(withId(R.id.btnOpenHashSolvingActivity)).check(matches(not(isDisplayed())))
        onView(withId(R.id.btnGetHashValue)).perform(click())

        onView(isRoot()).perform(waitFor(4000))
        onView(withId(R.id.btnOpenHashSolvingActivity)).perform(click())

        onView(withId(R.id.layoutLoadingData)).check(matches(isDisplayed()))

    }
    fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}