package ru.iteco.fmhandroid.ui.tests;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;

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
import ru.iteco.fmhandroid.ui.utils.ToastMatcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;

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

        // Инициализация decorView
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });

        // Получаем decorView из ActivityScenario
//        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());


        // Проверка состояния входа и выход из приложения, если вход выполнен
        logged.ensureLoggedOut();
    }

    @Test
    public void loginTest1() {

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
    public void invalidLoginTest() {
        DataHelper invalidLogin = DataHelper.invalidLogin();
        loginSteps.login(invalidLogin);
        // Проверка ошибки логина
        onView(withText("Something went wrong. Try again later."))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
//        onView(ViewMatchers.withText(Matchers.any(String.class)))
//                .inRoot(new ToastMatcher())
//                .check(matches(isDisplayed()));
    }

    @Test
    public void invalidPasswordTest() {
        DataHelper invalidPassword = DataHelper.invalidPassword();
        loginSteps.login(invalidPassword);
        // Проверка ошибки логина
        onView(withText("Something went wrong. Try again later."))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
//        onView(ViewMatchers.withText(Matchers.any(String.class)))
//                .inRoot(new ToastMatcher())
//                .check(matches(isDisplayed()));
    }

    @Test
    public void invalidCredentialsTest() {
        DataHelper invalidCredentials = DataHelper.invalidCredentials();
        loginSteps.login(invalidCredentials);
        // Проверка ошибки логина
        onView(withText("Something went wrong. Try again later."))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
//        onView(ViewMatchers.withText(Matchers.any(String.class)))
//                .inRoot(new ToastMatcher())
//                .check(matches(isDisplayed()));
    }

}
