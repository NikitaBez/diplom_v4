package ru.iteco.fmhandroid.ui.utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pageobjects.LoginPage;
import ru.iteco.fmhandroid.ui.pageobjects.MainPage;

public class AppManager {

    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();

    public void waitForElement(int viewId, long millis) {
        onView(isRoot()).perform(WaitForViewAction.waitDisplayed((viewId), millis));
    }

    public boolean isOnLoginPage() {
        try {
            waitForElement(R.id.login_text_input_layout, 5000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOnNewsPage() {
        try {
            waitForElement(R.id.all_news_text_view, 5000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void checkAndLogoutIfNecessary() {
        if (isOnNewsPage()) {
            mainPage.clickLogOffButton();
        } else if (isOnLoginPage()) {
            // If already on LoginPage, we can proceed without any action
        } else {
            throw new AssertionError("Приложение не находится на странице входа или новостей.");
        }
    }
}
