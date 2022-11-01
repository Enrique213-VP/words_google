package com.example.wordsapp

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NavigationTests {


    lateinit var  navController: TestNavHostController

    lateinit var exampleFragmentScenario: FragmentScenario<LetterListFragment>


    @Before
    fun setup(){
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        exampleFragmentScenario = launchFragmentInContainer(themeResId = R.style.Theme_Words)

        exampleFragmentScenario.onFragment { fragment ->

            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun navigate_to_words_nav_component() {

        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        assertEquals(navController.currentDestination?.id, R.id.wordListFragment)
    }
}