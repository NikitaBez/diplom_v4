package ru.iteco.fmhandroid.ui.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.WaitForViewAction;

public class LoginPage {

    public void enterLogin(String login) {
        onView(withHint("Login"))
                .check(matches(isDisplayed()))
                .perform(typeText(login), closeSoftKeyboard());
    }

    public void enterPassword(String password) {
        onView(withHint("Password"))
                .check(matches(isDisplayed()))
                .perform(typeText(password), closeSoftKeyboard());
    }

    public void clickLoginButton() {
        Allure.step("Тап по кнопке с id: " + R.id.enter_button);
        onView(withId(R.id.enter_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void waitForElement(int viewId, long timeout) {
        onView(isRoot()).perform(WaitForViewAction.waitDisplayed(viewId, timeout));
    }

    public void checkLoginPage(){
        onView(withId(R.id.enter_button))
                .check(matches(isDisplayed()));
    }
}
