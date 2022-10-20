package com.keneitec.theylonf.organizzeclone.view.signup

import android.view.View
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.android.material.textfield.TextInputLayout
import com.keneitec.theylonf.organizzeclone.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SignUpActivityTest() {

    @get:Rule
    val rule = activityScenarioRule<SignUpActivity>()

    @Test
    fun test_SingUpViewIsDisplayed() {
        val scenario = rule.scenario
        onView(withId(R.id.signUpContainer)).check(matches(isDisplayed()))
    }

    @Test
    fun test_VerifyIfShowMessageError1() {
        onView(withId(R.id.editNameTxt)).perform(click(), clearText())
        onView(withId(R.id.editEmailTxt)).perform(click(), clearText())
        onView(withId(R.id.editPasswordTxt)).perform(click(), clearText())
        closeSoftKeyboard()
        onView(withId(R.id.btnSignUp)).perform(click())

        onView(withId(R.id.editName)).check(matches(hasTextInputLayoutErrorText("Campo obrigatorio")))
    }

    @Test
    fun test_VerifyIfShowMessageError2() {
        onView(withId(R.id.editNameTxt)).perform(click())
            .perform(clearText(), typeText("Todo Mundo odeia o Chris"))
        onView(withId(R.id.editEmailTxt)).perform(clearText())
        onView(withId(R.id.editPasswordTxt)).perform(clearText())
        closeSoftKeyboard()
        onView(withId(R.id.btnSignUp)).perform(click())

        onView(withId(R.id.editEmail)).check(matches(hasTextInputLayoutErrorText("Campo obrigatorio")))
    }

    @Test
    fun test_VerifyIfShowMessageError3() {
        onView(withId(R.id.editNameTxt)).perform(click())
            .perform(clearText(), typeText("Todo Mundo odeia o Chris"))
        onView(withId(R.id.editPasswordTxt)).perform(clearText())
        closeSoftKeyboard()
        onView(withId(R.id.btnSignUp)).perform(click())

        onView(withId(R.id.editPassword)).check(matches(hasTextInputLayoutErrorText("Campo obrigatorio")))
    }

    @Test
    fun test_VerifyIfShowProgressBarAfterTextsViewsIsPretencion() {
        onView(withId(R.id.btnSignUp)).perform(click())

        onView(withId(R.id.createAccountProgressBar)).check(matches(isDisplayed()))
    }
}

fun hasTextInputLayoutErrorText(expectedErrorText: String): Matcher<View?>? {
    return object : TypeSafeMatcher<View?>() {

        override fun matchesSafely(view: View?): Boolean {
            if (view !is TextInputLayout) {
                return false
            }
            val error = (view as TextInputLayout).error ?: return false
            val hint = error.toString()
            return expectedErrorText == hint
        }

        override fun describeTo(description: Description?) {}
    }
}
