package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.LoginSteps;
import ru.iteco.fmhandroid.ui.pageobjects.NewsPage;
import ru.iteco.fmhandroid.ui.data.DataHelper;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest1 {

    private String LOGIN = "login2";
    private String PASSWORD = "password2";

    private LoginSteps loginSteps;
    private NewsPage newsPage;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        loginSteps = new LoginSteps();
        newsPage = new NewsPage();
    }

    @Test
    public void loginTest1() {

        // Получаем валидные данные для логина
        DataHelper validCredentials = DataHelper.validCredentials();

        // Логинимся
        loginSteps.login(validCredentials);

//        loginSteps.login(LOGIN, PASSWORD);

        // Проверка открытия главной страницы с блоком новостей
        newsPage.waitForElement(R.id.all_news_text_view, 5000);
        newsPage.verifyNewsSection();

        // Разлогинивание
        newsPage.clickAuthorizationButton();
        newsPage.waitForElement(android.R.id.title, 5000);
        newsPage.clickLogOffButton();
    }
}
