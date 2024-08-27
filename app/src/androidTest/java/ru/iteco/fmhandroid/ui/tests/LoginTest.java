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

    private void loginWithValidCredentialsAndVerify() {
        DataHelper validCredentials = DataHelper.validCredentials();
        loginSteps.login(validCredentials);
        mainPage.verifyMainPageWithShortNews();
    }

    @Test
    @DisplayName("Авторизация с действительным логином и паролем")
    @Description("Пользователь входит в приложение, используя действительные данные логина и пароля")
    public void validLoginAndPassword() {
        loginWithValidCredentialsAndVerify();
    }

    @Test
    @DisplayName("Ввод недействительного логина и действительного пароля")
    @Description("Ошибка входа в приложение после ввода действительного пароля и недействительного логина")
    public void invalidLoginAndValidPassword() {
        loginAndVerify(DataHelper.INVALID_LOGIN, DataHelper.VALID_PASSWORD, DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD);

    }

    @Test
    @DisplayName("Ввод действительного логина и недействительного пароля")
    @Description("Ошибка входа в приложение после ввода действительного логина и недействительного пароля")
    public void validLoginAndInvalidPassword() {
        loginAndVerify(DataHelper.VALID_LOGIN, DataHelper.INVALID_PASSWORD, DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD);
    }

    @DisplayName("Ввод недействительного логина и недействительного пароля")
    @Description("Ошибка входа в приложение после ввода недействительного логина и недействительного пароля")
    public void invalidLoginAndInvalidPassword() {
        loginAndVerify(DataHelper.INVALID_LOGIN, DataHelper.INVALID_PASSWORD, DataHelper.TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD);

    }

    @Test
    @DisplayName("Пустое поле логина и ввод действительного пароля")
    @Description("Ошибка входа в приложение после оставления поля логина пустым и ввода действительного пароля")
    public void emptyLoginAndValidPassword() {
        loginAndVerify("", DataHelper.VALID_PASSWORD, DataHelper.TOAST_MESSAGE_EMPTY_DATA);

    }

    @Test
    @DisplayName("Ввод действительного логина и пустое поле пароля")
    @Description("Ошибка входа в приложение после ввода действительного логина и оставления поля пароля пустым")
    public void validLoginAndEmptyPassword() {
        loginAndVerify(DataHelper.VALID_LOGIN, "", DataHelper.TOAST_MESSAGE_EMPTY_DATA);

    }

    @Test
    @DisplayName("Пустые поля логина и пароля")
    @Description("Ошибка входа в приложение после оставления полей логина и пароля пустыми")
    public void EmptyLoginAndEmptyPassword() {
        loginAndVerify("", "", DataHelper.TOAST_MESSAGE_EMPTY_DATA);

    }

    @Test
    @DisplayName("Тест выхода из аккаунта")
    @Description("Выход из аккаунта и переход на страницу авторизации")
    public void LogoutTest() {
        loginWithValidCredentialsAndVerify();
        mainPage.autoLogout();
        loginPage.checkLoginPage();
    }

    @Test
    @DisplayName("Тест ранее сохраненного логина")
    @Description("Вход с ранее сохраненным логином и паролем")
    public void SavedLoginTest() {
        loginWithValidCredentialsAndVerify();
        closeApp();
        restartApp();
        mainPage.verifyMainPageWithShortNews();
    }

    private void closeApp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> {activity.finishAffinity();
        });
    }

    private void restartApp() {
        ActivityScenario.launch(AppActivity.class);
        loginPage.waitForElement(R.id.authorization_image_button, 5000);
    }

}