package ru.iteco.fmhandroid.ui.tests;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.pageobjects.MainPage;
import ru.iteco.fmhandroid.ui.pageobjects.NewsPage;
import ru.iteco.fmhandroid.ui.steps.LoginSteps;
import ru.iteco.fmhandroid.ui.utils.Logged;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class NewsTest {
    private LoginSteps loginSteps;
    private MainPage mainPage;
    private NewsPage newsPage;
    private Logged logged;
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

        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());

        // Проверка состояния входа и выход из приложения, если вход выполнен
        logged.ensureLoggedOut();
    }

    @Test
    @DisplayName("Check full list of news list. ver 1")
    @Description("Display the full list of news by clicking on the ALL NEWS button")
    public void checkFullListOfNewsPage() {
        // Получаем валидные данные для логина
        DataHelper validCredentials = DataHelper.validCredentials();
        // Логинимся
        loginSteps.login(validCredentials);
        // Проверка открытия главной страницы с блоком новостей
        mainPage.verifyMainPageWithShortNews();
        //Переход на страницу новостей
        mainPage.clickAllNewsButton();
        newsPage.checkNewsPage();
        // Разлогинивание
//        mainPage.autoLogout();
    }

    @Test
    @DisplayName("Check full list of news list. ver 2 - hamburger")
    @Description("Display the full list of news by clicking on the hamburger")
    public void checkFullListOfNewsPageHamburger() {
        // Получаем валидные данные для логина
        DataHelper validCredentials = DataHelper.validCredentials();
        // Логинимся
        loginSteps.login(validCredentials);
        // Проверка открытия главной страницы с блоком новостей
        mainPage.verifyMainPageWithShortNews();
        //Переход на страницу новостей
        mainPage.clickHamburgerAndNews();
        newsPage.checkNewsPage();
        // Разлогинивание
//        mainPage.autoLogout();
    }

    @Test
    @DisplayName("Создание новой новости")
    @Description("В панели управления (Control Panel) создается новость с типом Объявление")
    public void createNewNews() {
        // Получаем валидные данные для логина
        DataHelper validCredentials = DataHelper.validCredentials();
        // Логинимся
        loginSteps.login(validCredentials);
        // Проверка открытия главной страницы с блоком новостей
        mainPage.verifyMainPageWithShortNews();
        //Переход на страницу новостей
        mainPage.clickHamburgerAndNews();
        newsPage.checkNewsPage();
        newsPage.clickEditButton();
        newsPage.createNews();
    }

    @Test
    @DisplayName("Удаление новости")
    @Description("Первая новость удалится и остальные карточки сместятся вверх")
    public void deleteNews() {
        // Получаем валидные данные для логина
        DataHelper validCredentials = DataHelper.validCredentials();
        // Логинимся
        loginSteps.login(validCredentials);
        // Проверка открытия главной страницы с блоком новостей
        mainPage.verifyMainPageWithShortNews();
        //Переход на страницу новостей
        mainPage.clickHamburgerAndNews();
        newsPage.checkNewsPage();
        newsPage.clickEditButton();
        newsPage.deleteNews();
    }

//    @Test
//    @DisplayName("Отмена Удаления новости")
//    @Description("Нажать ОТМЕНА в окне подтверждения удаления новости")
//    public void undoNewsDeletion() {
//        // Получаем валидные данные для логина
//        DataHelper validCredentials = DataHelper.validCredentials();
//        // Логинимся
//        loginSteps.login(validCredentials);
//        // Проверка открытия главной страницы с блоком новостей
//        mainPage.verifyMainPageWithShortNews();
//        //Переход на страницу новостей
//        mainPage.clickHamburgerAndNews();
//        newsPage.checkNewsPage();
//        newsPage.clickEditButton();
//        newsPage.undoNewsDeletion();
//    }
}
