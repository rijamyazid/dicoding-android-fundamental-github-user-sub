package com.example.githubuser

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.githubuser.util.DataConstant
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val firstFakeData = DataConstant.fakeUser1
    private val userDataTotal = 10

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadUsers() {
        Espresso.onView(withId(R.id.rv_users))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_users)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(userDataTotal)
        )
    }

    @Test
    fun loadUserDetail() {
        Espresso.onView(withId(R.id.rv_users)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Espresso.onView(withId(R.id.img_user))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.tv_name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_name))
            .check(ViewAssertions.matches(withText(firstFakeData.name)))

        Espresso.onView(withId(R.id.tv_username))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_username))
            .check(ViewAssertions.matches(withText(firstFakeData.username)))

        Espresso.onView(withId(R.id.tv_location))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_location))
            .check(ViewAssertions.matches(withText(firstFakeData.location)))

        Espresso.onView(withId(R.id.tv_company_content))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_company_content))
            .check(ViewAssertions.matches(withText(firstFakeData.company)))

        Espresso.onView(withId(R.id.tv_repository_content))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_repository_content))
            .check(ViewAssertions.matches(withText(firstFakeData.repository.toString())))

        Espresso.onView(withId(R.id.tv_followers_content))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_followers_content))
            .check(ViewAssertions.matches(withText(firstFakeData.follower.toString())))

        Espresso.onView(withId(R.id.tv_following_content))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_following_content))
            .check(ViewAssertions.matches(withText(firstFakeData.following.toString())))
    }

}