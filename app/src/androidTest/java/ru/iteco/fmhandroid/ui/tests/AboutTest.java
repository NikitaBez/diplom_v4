package ru.iteco.fmhandroid.ui.tests;

import org.junit.After;
import org.junit.Test;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.data.DataHelper;

import android.content.Intent;
import android.view.View;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.intent.Intents.intended;

import static org.hamcrest.Matchers.allOf;

import org.junit.Before;
import org.junit.Rule;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.LoginSteps;
import ru.iteco.fmhandroid.ui.pageobjects.MainPage;
import ru.iteco.fmhandroid.ui.pageobjects.NewsPage;
import ru.iteco.fmhandroid.ui.pageobjects.QuotesPage;
import ru.iteco.fmhandroid.ui.pageobjects.AboutPage;
import ru.iteco.fmhandroid.ui.utils.Logged;

public class AboutTest {

    private LoginSteps loginSteps;
    private MainPage mainPage;
    private NewsPage newsPage;
    private Logged logged;
    private QuotesPage quotesPage;
    private AboutPage aboutPage;
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        loginSteps = new LoginSteps();
        mainPage = new MainPage();
        newsPage = new NewsPage();
        logged = new Logged();
        quotesPage = new QuotesPage();
        aboutPage = new AboutPage();
        Intents.init();
        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());

        // Проверка состояния входа и выход из приложения, если вход выполнен
        logged.ensureLoggedOut();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    @DisplayName("Displaying the application version")
    @Description("You can view the application version")
    public void checkAppVersion() {
        // Получаем валидные данные для логина
        DataHelper validCredentials = DataHelper.validCredentials();
        // Логинимся
        loginSteps.login(validCredentials);
        // Проверка открытия главной страницы с блоком новостей
        mainPage.verifyMainPageWithShortNews();
        //Переход на страницу About
        mainPage.clickHamburgerAndAbout();
        aboutPage.checkVersionOfApp();
        aboutPage.clickBackButton();
        // Разлогинивание
//        mainPage.autoLogout();
    }

    @Test
    @DisplayName("Link to privacy policy")
    @Description("A web page with the privacy policy will open")
    public void checkPrivacyPolicyLink() {
// Получаем валидные данные для логина
        DataHelper validCredentials = DataHelper.validCredentials();
        // Логинимся
        loginSteps.login(validCredentials);
        // Проверка открытия главной страницы с блоком новостей
        mainPage.verifyMainPageWithShortNews();
        //Переход на страницу About
        mainPage.clickHamburgerAndAbout();
        aboutPage.clickPrivacyPolicy();
        // Проверяем, что был передан Intent для открытия браузера с правильным URL
        aboutPage.checkForIntendedToOpenPrivacyPolicy();


    }

    @Test
    @DisplayName("Link to terms of use")
    @Description("A web page with the terms of use will open")
    public void checkTermsOfUseLink() {
// Получаем валидные данные для логина
        DataHelper validCredentials = DataHelper.validCredentials();
        // Логинимся
        loginSteps.login(validCredentials);
        // Проверка открытия главной страницы с блоком новостей
        mainPage.verifyMainPageWithShortNews();
        //Переход на страницу About
        mainPage.clickHamburgerAndAbout();
        aboutPage.clickTermsOfUse();
        // Проверяем, что был передан Intent для открытия браузера с правильным URL
        aboutPage.checkForIntendedToOpenTermsOfUse();
    }

    @Test
    @DisplayName("Copyright Check")
    @Description("Displaying the copyright owner, checking the operation of the \"Return\" button")
    public void copyrightCheck() {
        // Получаем валидные данные для логина
        DataHelper validCredentials = DataHelper.validCredentials();
        // Логинимся
        loginSteps.login(validCredentials);
        // Проверка открытия главной страницы с блоком новостей
        mainPage.verifyMainPageWithShortNews();
        //Переход на страницу About
        mainPage.clickHamburgerAndAbout();
        aboutPage.copyrightCheck();
        aboutPage.clickBackButton();
    }

}
