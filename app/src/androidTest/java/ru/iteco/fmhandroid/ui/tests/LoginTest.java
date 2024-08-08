package ru.iteco.fmhandroid.ui.tests;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.LoginSteps;
import ru.iteco.fmhandroid.ui.pageobjects.MainPage;
import ru.iteco.fmhandroid.ui.pageobjects.LoginPage;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.utils.Logged;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.Matchers.not;

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

        // Проверка состояния входа и выход из приложения, если вход выполнен
        logged.ensureLoggedOut();
    }

    @Test
    @DisplayName("Authorization with valid login and password")
    @Description("The user logs into the application using valid login and password data")
    public void validLoginAndPassword() {
        // Получаем валидные данные для логина
        DataHelper validCredentials = DataHelper.validCredentials();
        // Логинимся
        loginSteps.login(validCredentials);
        // Проверка открытия главной страницы с блоком новостей
        //mainPage.waitForElement(R.id.all_news_text_view, 5000);
        mainPage.verifyMainPageWithShortNews();
        // Разлогинивание
        mainPage.autoLogout();
    }

    @Test
    @DisplayName("Entering an invalid login and a valid password")
    @Description("Error logging into the application after entering an invalid login and valid password")
    public void invalidLoginAndValidPassword() {
        DataHelper invalidLogin = DataHelper.invalidLogin();
        loginSteps.invalidCredentials(invalidLogin);
        onView(withText(DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Entering an valid login and a invalid password")
    @Description("Error logging into the application after entering an valid login and invalid password")
    public void validLoginAndInvalidPassword() {
        DataHelper invalidPassword = DataHelper.invalidPassword();
        loginSteps.invalidCredentials(invalidPassword);
        onView(withText(DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Entering an invalid login and a invalid password")
    @Description("Error logging into the application after entering an invalid login and invalid password")
    public void invalidLoginAndInvalidPassword() {
        DataHelper invalidCredentials = DataHelper.invalidCredentials();
        loginSteps.invalidCredentials(invalidCredentials);
        onView(withText(DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Empty login field and enter a valid password")
    @Description("Error logging into the application after not entering login and entering valid password")
    public void emptyLoginAndValidPassword() {
        DataHelper emptyLogin = DataHelper.emptyLogin();
        loginSteps.invalidCredentials(emptyLogin);
        onView(withText(DataHelper.TOAST_MESSAGE_EMPTY_DATA))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Entering a valid password and the password field empty")
    @Description("Error logging into the application after entering valid login and not entering password")
    public void validLoginAndEmptyPassword() {
        DataHelper emptyPassword = DataHelper.emptyPassword();
        loginSteps.invalidCredentials(emptyPassword);
        onView(withText(DataHelper.TOAST_MESSAGE_EMPTY_DATA))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    @DisplayName("Empty login and password fields")
    @Description("Error logging into the application after not entering login and password")
    public void EmptyLoginAndEmptyPassword() {
        DataHelper emptyCredentials = DataHelper.emptyCredentials();
        loginSteps.invalidCredentials(emptyCredentials);
        onView(withText(DataHelper.TOAST_MESSAGE_EMPTY_DATA))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
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

        // Вход с ранее сохраненными Логином и Паролем
        loginSteps.login(validCredentials);

        // Проверка, что основная страница с краткими новостями видима
        mainPage.verifyMainPageWithShortNews();

        // Закрытие приложения
        closeApp();

        // Перезапуск приложения
        restartApp();

        // Повторная проверка, что основная страница с краткими новостями снова видима
        mainPage.verifyMainPageWithShortNews();
    }

    // Метод для закрытия приложения
    private void closeApp() {
        // Ждем, пока элемент не станет видимым (например, чтобы убедиться, что активность готова к закрытию)
        loginPage.waitForElement(R.id.login_text_input_layout, 5000);

        // Завершаем активность
        mActivityScenarioRule.getScenario().onActivity(activity -> {
            activity.finishAffinity();  // Завершение активности, но не всего процесса
        });
    }

    // Метод для перезапуска приложения
    private void restartApp() {
        // Перезапускаем активность
        ActivityScenario.launch(AppActivity.class);

        // Ждем, пока элемент не станет видимым после перезапуска
        loginPage.waitForElement(R.id.login_text_input_layout, 5000);
    }

}