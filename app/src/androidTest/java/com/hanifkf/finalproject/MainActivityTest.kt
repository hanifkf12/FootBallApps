package com.hanifkf.finalproject

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.hanifkf.finalproject.Main.MainActivity
import com.hanifkf.finalproject.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testTeams(){
        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(navigation_teams)).perform(click())
        onView(withId(app_bar_search)).check(matches(isDisplayed()))
        onView(withId(spinner_teams)).check(matches(isDisplayed()))
        onView(withId(spinner_teams)).perform(click())
        onView(withText("Spanish La Liga")).check(matches(isDisplayed()))
        onView(withText("Spanish La Liga")).perform(click())
        onView(withId(recycle_teams)).check(matches(isDisplayed()))
        onView(withId(recycle_teams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(recycle_teams)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        Espresso.pressBack()
        onView(withId(spinner_teams)).check(matches(isDisplayed()))
        onView(withId(spinner_teams)).perform(click())
        onView(withText("German Bundesliga")).check(matches(isDisplayed()))
        onView(withText("German Bundesliga")).perform(click())
        onView(withId(recycle_teams)).check(matches(isDisplayed()))
        onView(withId(recycle_teams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))
        onView(withId(recycle_teams)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(15, click()))
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun testMatch(){
        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(tab_matches)).check(matches(isDisplayed()))
        onView(withId(spinner_last)).check(matches(isDisplayed()))
        onView(withId(spinner_last)).perform(click())
        onView(withText("Spanish La Liga")).check(matches(isDisplayed()))
        onView(withText("Spanish La Liga")).perform(click())
        onView(withId(recycle_last)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(recycle_last)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        Espresso.pressBack()
        onView(withId(tab_matches)).check(matches(isDisplayed()))
        onView(withText("NEXT")).perform(click())
        onView(withId(spinner_next)).check(matches(isDisplayed()))
        onView(withId(spinner_next)).perform(click())
        onView(withText("German Bundesliga")).check(matches(isDisplayed()))
        onView(withText("German Bundesliga")).perform(click())
        onView(withId(recycle_next)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(recycle_next)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun testFavorite(){
        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(navigation_favorites)).perform(click())
        onView(withId(tab_favorites)).check(matches(isDisplayed()))
        onView(withText("TEAMS")).perform(click())
        onView(withId(recycle_fav_team)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(recycle_fav_team)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        Espresso.pressBack()
        onView(withId(tab_favorites)).check(matches(isDisplayed()))
        onView(withText("MATCHES")).perform(click())
        onView(withId(recycle_fav)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(recycle_fav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun testPlayer(){
        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(navigation_teams)).perform(click())
        onView(withId(app_bar_search)).check(matches(isDisplayed()))
        onView(withId(spinner_teams)).check(matches(isDisplayed()))
        onView(withId(spinner_teams)).perform(click())
        onView(withText("Spanish La Liga")).check(matches(isDisplayed()))
        onView(withText("Spanish La Liga")).perform(click())
        onView(withId(recycle_teams)).check(matches(isDisplayed()))
        onView(withId(recycle_teams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(recycle_teams)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(tab_detail_teams)).check(matches(isDisplayed()))
        onView(withText("PLAYERS")).perform(click())
        onView(withId(recycle_player)).check(matches(isDisplayed()))
        onView(withId(recycle_player)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(recycle_player)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.pressBack()
        Espresso.pressBack()
        onView(withId(spinner_teams)).check(matches(isDisplayed()))
        onView(withId(spinner_teams)).perform(click())
        onView(withText("German Bundesliga")).check(matches(isDisplayed()))
        onView(withText("German Bundesliga")).perform(click())
        onView(withId(recycle_teams)).check(matches(isDisplayed()))
        onView(withId(recycle_teams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(recycle_teams)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(tab_detail_teams)).check(matches(isDisplayed()))
        onView(withText("PLAYERS")).perform(click())
        onView(withId(recycle_player)).check(matches(isDisplayed()))
        onView(withId(recycle_player)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(recycle_player)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.pressBack()
        Espresso.pressBack()
    }

}