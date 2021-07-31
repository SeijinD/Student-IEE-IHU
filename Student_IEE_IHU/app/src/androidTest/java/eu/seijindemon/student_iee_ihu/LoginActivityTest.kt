package eu.seijindemon.student_iee_ihu

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import eu.seijindemon.student_iee_ihu.ui.auth.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @Rule @JvmField
    var activityRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun login() {
        onView(withId(R.id.login_email)).perform(replaceText("georgekara2010@yahoo.gr"))
        onView(withId(R.id.login_password)).perform(replaceText("password"))
        onView(withId(R.id.login_button)).perform(click())
    }

}