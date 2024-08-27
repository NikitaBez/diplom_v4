package ru.iteco.fmhandroid.ui.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

import androidx.test.espresso.intent.matcher.IntentMatchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.utils.WaitForViewAction;

public class AboutPage {

    public void checkVersionOfApp() {
        onView(withId(R.id.about_version_value_text_view))
                .check(matches(isDisplayed()));
    }

    public void clickBackButton() {
        onView(withId(R.id.about_back_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickPrivacyPolicy() {
        onView(withId(R.id.about_privacy_policy_value_text_view))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickTermsOfUse() {
        onView(withId(R.id.about_terms_of_use_value_text_view))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void waitForElement(int viewId, long timeout) {
        onView(isRoot()).perform(WaitForViewAction.waitDisplayed(viewId, timeout));
    }

    public void checkForIntendedToOpenPrivacyPolicy() {
        intended(allOf(
                IntentMatchers.hasAction(Intent.ACTION_VIEW),
                IntentMatchers.hasData(DataHelper.VALUE_LINK_OF_PRIVACY_POLICY)
        ));
    }

    public void checkForIntendedToOpenTermsOfUse() {
        intended(allOf(
                IntentMatchers.hasAction(Intent.ACTION_VIEW),
                IntentMatchers.hasData(DataHelper.VALUE_LINK_OF_TERMS_OF_USE)
        ));
    }

    public void copyrightCheck() {
        onView(withId(R.id.about_company_info_label_text_view))
                .check(matches(isDisplayed()));
    }
}
