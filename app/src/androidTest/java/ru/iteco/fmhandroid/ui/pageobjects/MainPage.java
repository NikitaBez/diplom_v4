package ru.iteco.fmhandroid.ui.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.WaitForViewAction;

public class MainPage {

    public void verifyMainPageWithShortNews() {
        onView(withId(R.id.all_news_text_view))
                .check(matches(isDisplayed()))
                .check(matches(withText("ALL NEWS")));
    }

    public void clickAuthorizationButton() {
        Allure.step("Тап по кнопке с id: " + R.id.authorization_image_button);
        onView(withId(R.id.authorization_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickLogOffButton() {
        Allure.step("Тап по кнопке с id: " + android.R.id.title);
        onView(withId(android.R.id.title))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void waitForElement(int viewId, long timeout) {
        onView(isRoot()).perform(WaitForViewAction.waitDisplayed(viewId, timeout));
    }

    public void autoLogout(){
        clickAuthorizationButton();
        waitForElement(android.R.id.title, 5000);
        clickLogOffButton();
    }

    public void clickAllNewsButton() {
        Allure.step("Тап по кнопке с id: " + R.id.all_news_text_view);
        onView(withId(R.id.all_news_text_view))
                .check(matches(isDisplayed()))
                .check(matches(withText("ALL NEWS")))
                .perform(click());
    }

    public void clickHamburgerAndNews() {
        Allure.step("Тап по кнопке с id: " + R.id.main_menu_image_button);
            onView(withId(R.id.main_menu_image_button))
                    .check(matches(isDisplayed()))
                    .perform(click());
        onView(allOf(withId(android.R.id.title), withText("News")))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickHamburgerAndAbout() {
        Allure.step("Тап по кнопке с id: " + R.id.main_menu_image_button);
        onView(withId(R.id.main_menu_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(allOf(withId(android.R.id.title), withText("About")))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public  void clickQuotesButton() {
        Allure.step("Тап по кнопке с id: " + R.id.our_mission_image_button);
        onView(withId(R.id.our_mission_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }
}
