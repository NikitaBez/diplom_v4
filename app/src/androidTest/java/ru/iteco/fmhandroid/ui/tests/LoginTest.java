package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.pageobjects.LoginPage;
import ru.iteco.fmhandroid.ui.pageobjects.MainPage;
import ru.iteco.fmhandroid.ui.steps.LoginSteps;
import ru.iteco.fmhandroid.ui.utils.Logged;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class LoginTest {

    private LoginSteps loginSteps;
    private MainPage mainPage;
    private LoginPage loginPage;
    private Logged logged;
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        loginSteps = new LoginSteps();
        mainPage = new MainPage();
        loginPage = new LoginPage();
        logged = new Logged();

        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());

        logged.ensureLoggedOut();
    }

    private void loginAndVerify(String login, String password, String expectedMessage) {
        DataHelper credentials = new DataHelper(login, password);
        loginSteps.invalidCredentials(credentials);
        onView(withText(expectedMessage))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Authorization with valid login and password")
    @Description("The user logs into the application using valid login and password data")
    public void validLoginAndPassword() {
        DataHelper validCredentials = DataHelper.validCredentials();
        loginSteps.login(validCredentials);
        mainPage.verifyMainPageWithShortNews();
    }

    @Test
    @DisplayName("Entering an invalid login and a valid password")
    @Description("Error logging into the application after entering an invalid login and valid password")
    public void invalidLoginAndValidPassword() {
//        DataHelper invalidLogin = DataHelper.invalidLogin();
//        loginSteps.invalidCredentials(invalidLogin);
//        onView(withText(DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD))
//                .inRoot(withDecorView(Matchers.not(decorView)))
//                .check(matches(isDisplayed()));
        loginAndVerify(DataHelper.INVALID_LOGIN, DataHelper.VALID_PASSWORD, DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD);

    }

    @Test
    @DisplayName("Entering an valid login and a invalid password")
    @Description("Error logging into the application after entering an valid login and invalid password")
    public void validLoginAndInvalidPassword() {
//        DataHelper invalidPassword = DataHelper.invalidPassword();
//        loginSteps.invalidCredentials(invalidPassword);
//        onView(withText(DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD))
//                .inRoot(withDecorView(Matchers.not(decorView)))
//                .check(matches(isDisplayed()));
        loginAndVerify(DataHelper.VALID_LOGIN, DataHelper.INVALID_PASSWORD, DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD);
    }

    @Test
    @DisplayName("Entering an invalid login and a invalid password")
    @Description("Error logging into the application after entering an invalid login and invalid password")
    public void invalidLoginAndInvalidPassword() {
//        DataHelper invalidCredentials = DataHelper.invalidCredentials();
//        loginSteps.invalidCredentials(invalidCredentials);
//        onView(withText(DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD))
//                .inRoot(withDecorView(Matchers.not(decorView)))
//                .check(matches(isDisplayed()));
        loginAndVerify(DataHelper.INVALID_LOGIN, DataHelper.INVALID_PASSWORD, DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD);

    }

    @Test
    @DisplayName("Empty login field and enter a valid password")
    @Description("Error logging into the application after not entering login and entering valid password")
    public void emptyLoginAndValidPassword() {
//        DataHelper emptyLogin = DataHelper.emptyLogin();
//        loginSteps.invalidCredentials(emptyLogin);
//        onView(withText(DataHelper.TOAST_MESSAGE_EMPTY_DATA))
//                .inRoot(withDecorView(Matchers.not(decorView)))
//                .check(matches(isDisplayed()));
        loginAndVerify("", DataHelper.VALID_PASSWORD, DataHelper.TOAST_MESSAGE_EMPTY_DATA);

    }

    @Test
    @DisplayName("Entering a valid password and the password field empty")
    @Description("Error logging into the application after entering valid login and not entering password")
    public void validLoginAndEmptyPassword() {
//        DataHelper emptyPassword = DataHelper.emptyPassword();
//        loginSteps.invalidCredentials(emptyPassword);
//        onView(withText(DataHelper.TOAST_MESSAGE_EMPTY_DATA))
//                .inRoot(withDecorView(Matchers.not(decorView)))
//                .check(matches(isDisplayed()));
        loginAndVerify(DataHelper.VALID_LOGIN, "", DataHelper.TOAST_MESSAGE_EMPTY_DATA);

    }

    @Test
    @DisplayName("Empty login and password fields")
    @Description("Error logging into the application after not entering login and password")
    public void EmptyLoginAndEmptyPassword() {
//        DataHelper emptyCredentials = DataHelper.emptyCredentials();
//        loginSteps.invalidCredentials(emptyCredentials);
//        onView(withText(DataHelper.TOAST_MESSAGE_EMPTY_DATA))
//                .inRoot(withDecorView(Matchers.not(decorView)))
//                .check(matches(isDisplayed()));
        loginAndVerify("", "", DataHelper.TOAST_MESSAGE_EMPTY_DATA);

    }

    @Test
    @DisplayName("Logout test")
    @Description("Logout. Go to the authorization page")
    public void LogoutTest() {
        DataHelper validCredentials = DataHelper.validCredentials();
        loginSteps.login(validCredentials);
        mainPage.verifyMainPageWithShortNews();
        mainPage.autoLogout();
        loginPage.checkLoginPage();
    }

    @Test
    @DisplayName("Previously saved Login test")
    @Description("Login with previously saved Login and Password")
    public void SavedLoginTest() {
        DataHelper validCredentials = DataHelper.validCredentials();
        loginSteps.login(validCredentials);
        mainPage.verifyMainPageWithShortNews();
        closeApp();
        restartApp();
        mainPage.verifyMainPageWithShortNews();
    }

    // Метод для закрытия приложения
    private void closeApp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            activity.finishAffinity();
        });
    }

    private void restartApp() {
        ActivityScenario.launch(AppActivity.class);
        loginPage.waitForElement(R.id.authorization_image_button, 5000);
    }

}