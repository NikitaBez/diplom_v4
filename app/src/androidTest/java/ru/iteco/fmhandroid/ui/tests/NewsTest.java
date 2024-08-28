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
        logged.ensureLoggedOut();
    }

    private void loginAndNavigateToNewsPage() {
        DataHelper validCredentials = DataHelper.validCredentials();
        loginSteps.login(validCredentials);
        mainPage.verifyMainPageWithShortNews();
    }

    private void navigateToFullNewsList() {
        mainPage.clickHamburgerAndNews();
        newsPage.checkNewsPage();
    }

    private void navigateToAllNewsButton() {
        mainPage.clickAllNewsButton();
        newsPage.checkNewsPage();
    }

    @Test
    @DisplayName("Проверка полного списка новостей через кнопку ALL NEWS")
    @Description("Отображение полного списка новостей при нажатии на кнопку ALL NEWS")
    public void checkFullListOfNewsPage() {
        loginAndNavigateToNewsPage();
        navigateToAllNewsButton();
    }

    @Test
    @DisplayName("Проверка полного списка новостей через гамбургер-меню")
    @Description("Отображение полного списка новостей при нажатии на гамбургер-меню")
    public void checkFullListOfNewsPageHamburger() {
        loginAndNavigateToNewsPage();
        navigateToFullNewsList();
    }

    @Test
    @DisplayName("Создание новой новости")
    @Description("В панели управления (Control Panel) создается новость с типом Объявление")
    public void createNewNews() {
        loginAndNavigateToNewsPage();
        navigateToFullNewsList();
        newsPage.clickEditButton();
        newsPage.createNews();
    }

    @Test
    @DisplayName("Удаление новости")
    @Description("Созданная новость, будет найдена в списке новостей и удалена")
    public void deleteNews() {
        loginAndNavigateToNewsPage();
        navigateToFullNewsList();
        newsPage.clickEditButton();
        newsPage.createNews();
        navigateToFullNewsList();
        newsPage.clickEditButton();
        newsPage.deleteNews();
    }

    @Test
    @DisplayName("Отмена Удаления новости")
    @Description("Нажать ОТМЕНА в окне подтверждения удаления новости")
    public void undoNewsDeletion() {
        loginAndNavigateToNewsPage();
        navigateToFullNewsList();
        newsPage.clickEditButton();
        newsPage.createNews();
        newsPage.undoNewsDeletion();
    }
}
