package tour.donnees.github.presentation

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.loadKoinModules
import tour.donnees.github.config.MockNetwork
import tour.donnees.github.config.MockRepository
import tour.donnees.github.config.MockUseCase
import tour.donnees.github.config.MockViewModel
import tour.donnees.github.presentation.view.MainActivity

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun beforeTest() {
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("tour.donnees.github", appContext.packageName)
    }


    @Test
    fun test() {
        Thread.sleep(3000)
        Espresso.onView(withText("kotlin")).check(matches(isDisplayed()))
        Espresso.onView(withText("JetBrains")).check(matches(isDisplayed()))
        Espresso.onView(withText("The Kotlin Programming Language. ")).check(matches(isDisplayed()))
        Espresso.onView(withText("44.2K")).check(matches(isDisplayed()))
        Espresso.onView(withText("5.5K")).check(matches(isDisplayed()))
    }
}