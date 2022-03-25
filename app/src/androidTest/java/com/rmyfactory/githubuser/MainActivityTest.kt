package com.rmyfactory.githubuser

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.rmyfactory.githubuser.util.EspressoIdlingResource
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class MainActivityTest {

    private val userDataTotal = 10

    @get:Rule
    var activityRule = ActivityScenarioRule(com.rmyfactory.githubuser.MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        IdlingPolicies.setMasterPolicyTimeout(1, TimeUnit.MINUTES)
        IdlingPolicies.setIdlingResourceTimeout(1, TimeUnit.MINUTES)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadUsers() {
        onView(withId(com.rmyfactory.githubuser.R.id.rv_users)).check(matches(isDisplayed()))
        onView(withId(com.rmyfactory.githubuser.R.id.rv_users)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(userDataTotal)
        )
    }

    @Test
    fun loadUserDetail() {
        onView(withId(com.rmyfactory.githubuser.R.id.rv_users)).check(matches(isDisplayed()))
        onView(withId(com.rmyfactory.githubuser.R.id.rv_users)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(com.rmyfactory.githubuser.R.id.img_user)).check(matches(isDisplayed()))
        onView(withId(com.rmyfactory.githubuser.R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(com.rmyfactory.githubuser.R.id.tv_username)).check(matches(isDisplayed()))
        onView(withId(com.rmyfactory.githubuser.R.id.tv_location)).check(matches(isDisplayed()))
        onView(withId(com.rmyfactory.githubuser.R.id.tv_company_content)).check(matches(isDisplayed()))
        onView(withId(com.rmyfactory.githubuser.R.id.tv_repository_content)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(com.rmyfactory.githubuser.R.id.tv_followers_content)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(com.rmyfactory.githubuser.R.id.tv_following_content)).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun loadFollowers() {
        onView(withId(com.rmyfactory.githubuser.R.id.rv_users)).check(matches(isDisplayed()))
        onView(withId(com.rmyfactory.githubuser.R.id.rv_users)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(com.rmyfactory.githubuser.R.id.rv_followers)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFollowing() {
        onView(withId(com.rmyfactory.githubuser.R.id.rv_users)).check(matches(isDisplayed()))
        onView(withId(com.rmyfactory.githubuser.R.id.rv_users)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withText("FOLLOWING")).perform(click())
        onView(withId(com.rmyfactory.githubuser.R.id.rv_following)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavorite() {
        onView(withId(com.rmyfactory.githubuser.R.id.rv_users)).check(matches(isDisplayed()))
        onView(withId(com.rmyfactory.githubuser.R.id.rv_users)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(com.rmyfactory.githubuser.R.id.fab_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(com.rmyfactory.githubuser.R.id.favoriteFragment)).perform(click())
        onView(withId(com.rmyfactory.githubuser.R.id.rv_favorite)).check(matches(isDisplayed()))
        onView(withId(com.rmyfactory.githubuser.R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(com.rmyfactory.githubuser.R.id.tv_username)).check(matches(isDisplayed()))
    }

    @Test
    fun loadSearchResult() {
        onView(withId(com.rmyfactory.githubuser.R.id.appbar_search)).perform(click())
        onView(withId(com.rmyfactory.githubuser.R.id.search_view_search)).perform(
            click(),
            typeSearchViewText("rijamyazid")
        )
        onView(withId(com.rmyfactory.githubuser.R.id.rv_search)).check(matches(isDisplayed()))
        onView(withId(com.rmyfactory.githubuser.R.id.rv_search)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(com.rmyfactory.githubuser.R.id.tv_username)).check(matches(withText("rijamyazid")))
    }

    private fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun getDescription(): String {
                return "Change view text"
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as SearchView).setQuery(text, false)
            }

        }
    }

}