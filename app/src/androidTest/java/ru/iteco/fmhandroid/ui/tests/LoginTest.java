package ru.iteco.fmhandroid.ui.tests;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.LoginSteps;
import ru.iteco.fmhandroid.ui.pageobjects.NewsPage;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.utils.Logged;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest {

    private LoginSteps loginSteps;
    private NewsPage newsPage;
    private Logged logged;
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        loginSteps = new LoginSteps();
        newsPage = new NewsPage();
        logged = new Logged();

        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());

        // Проверка состояния входа и выход из приложения, если вход выполнен
        logged.ensureLoggedOut();
    }

    @Test
    public void validLoginAndPassword() {
        // Получаем валидные данные для логина
        DataHelper validCredentials = DataHelper.validCredentials();
        // Логинимся
        loginSteps.login(validCredentials);
        // Проверка открытия главной страницы с блоком новостей
        newsPage.waitForElement(R.id.all_news_text_view, 5000);
        newsPage.verifyNewsSection();
        // Разлогинивание
        newsPage.clickAuthorizationButton();
        newsPage.waitForElement(android.R.id.title, 5000);
        newsPage.clickLogOffButton();
    }

    @Test
    public void invalidLoginAndValidPassword() {
        DataHelper invalidLogin = DataHelper.invalidLogin();
        loginSteps.invalidCredentials(invalidLogin);
        onView(withText(DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void validLoginAndInvalidPassword() {
        DataHelper invalidPassword = DataHelper.invalidPassword();
        loginSteps.invalidCredentials(invalidPassword);
        onView(withText(DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void invalidLoginAndInvalidPassword() {
        DataHelper invalidCredentials = DataHelper.invalidCredentials();
        loginSteps.invalidCredentials(invalidCredentials);
        onView(withText(DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void emptyLoginAndValidPassword() {
        DataHelper emptyLogin = DataHelper.emptyLogin();
        loginSteps.invalidCredentials(emptyLogin);
        onView(withText(DataHelper.TOAST_MESSAGE_EMPTY_DATA))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void validLoginAndEmptyPassword() {
        DataHelper emptyPassword = DataHelper.emptyPassword();
        loginSteps.invalidCredentials(emptyPassword);
        onView(withText(DataHelper.TOAST_MESSAGE_EMPTY_DATA))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void EmptyLoginAndEmptyPassword() {
        DataHelper emptyCredentials = DataHelper.emptyCredentials();
        loginSteps.invalidCredentials(emptyCredentials);
        onView(withText(DataHelper.TOAST_MESSAGE_EMPTY_DATA))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }
}
