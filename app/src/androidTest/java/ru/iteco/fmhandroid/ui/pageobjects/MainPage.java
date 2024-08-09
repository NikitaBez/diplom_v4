package ru.iteco.fmhandroid.ui.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.WaitForViewAction;

public class MainPage {

    //верификация открытия главной страницы
    public void verifyMainPageWithShortNews() {
        onView(withId(R.id.all_news_text_view))
                .check(matches(isDisplayed()))
                .check(matches(withText("ALL NEWS")));
    }

    //нажатие на иконку авторизации для последующего выхода
    public void clickAuthorizationButton() {
        onView(withId(R.id.authorization_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    //нажатие на кнопку выхода
    public void clickLogOffButton() {
        onView(withId(android.R.id.title))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    //метод ожидания
    public void waitForElement(int viewId, long timeout) {
        onView(isRoot()).perform(WaitForViewAction.waitDisplayed(viewId, timeout));
    }

    public void autoLogout(){
        clickAuthorizationButton();
        waitForElement(android.R.id.title, 5000);
        clickLogOffButton();
    }

    //нажатие на кнопку ALL NEWS
    public void clickAllNewsButton() {
        onView(withId(R.id.all_news_text_view))
                .check(matches(isDisplayed()))
                .check(matches(withText("ALL NEWS")))
                .perform(click());
    }

    //нажатие на кнопку Гамбургер
    public void clickHamburgerAndNews() {
            onView(withId(R.id.main_menu_image_button))
                    .check(matches(isDisplayed()))
//                .check(matches(withText("ALL NEWS")))
                    .perform(click());
        onView(allOf(withId(android.R.id.title), withText("News")))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    //нажатие на кнопку Гамбургер
    public void clickHamburgerAndAbout() {
        onView(withId(R.id.main_menu_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(allOf(withId(android.R.id.title), withText("About")))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public  void clickQuotesButton() {
        onView(withId(R.id.our_mission_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }
}
