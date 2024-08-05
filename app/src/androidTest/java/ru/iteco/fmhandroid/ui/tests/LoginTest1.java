package ru.iteco.fmhandroid.ui.tests;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
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
public class LoginTest1 {

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
    public void loginTest1() { //Проверка входа в систему с валидными значениями логина и пароля

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
    public void invalidLoginTest() { //Проверка входа в систему с невалидным значением логина
        DataHelper invalidLogin = DataHelper.invalidLogin();
        loginSteps.invalidCredentials(invalidLogin);
        // Проверка Toast message
        onView(withText(DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void invalidPasswordTest() { //Проверка входа в систему с невалидным значением пароля
        DataHelper invalidPassword = DataHelper.invalidPassword();
        loginSteps.invalidCredentials(invalidPassword);
        // Проверка Toast message
        onView(withText(DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void invalidCredentialsTest() { //Проверка входа в систему с невалидными значениями логина и пароля
        DataHelper invalidCredentials = DataHelper.invalidCredentials();
        loginSteps.invalidCredentials(invalidCredentials);
        // Проверка Toast message
        onView(withText(DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void emptyLogin() { //Проверка входа в систему с пустым полем логина
        DataHelper emptyLogin = DataHelper.emptyLogin();
        loginSteps.invalidCredentials(emptyLogin);
        // Проверка Toast message
        onView(withText(DataHelper.TOAST_MESSAGE_EMPTY_DATA))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }
    @Test
    public void emptyPassword() { //Проверка входа в систему с пустым полем пароля
        DataHelper emptyPassword = DataHelper.emptyPassword();
        loginSteps.invalidCredentials(emptyPassword);
        // Проверка Toast message
        onView(withText(DataHelper.TOAST_MESSAGE_EMPTY_DATA))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void emptyCredentials() { //Проверка входа в систему с пустымями логина и пароля
        DataHelper emptyCredentials = DataHelper.emptyCredentials();
        loginSteps.invalidCredentials(emptyCredentials);
        // Проверка Toast message
        onView(withText(DataHelper.TOAST_MESSAGE_EMPTY_DATA))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }
}
