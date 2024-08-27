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
import ru.iteco.fmhandroid.ui.pageobjects.QuotesPage;
import ru.iteco.fmhandroid.ui.steps.LoginSteps;
import ru.iteco.fmhandroid.ui.utils.Logged;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class QuotesTest {
    private LoginSteps loginSteps;
    private MainPage mainPage;
    private NewsPage newsPage;
    private Logged logged;
    private QuotesPage quotesPage;
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
        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
        logged.ensureLoggedOut();
    }

    @Test
    @DisplayName("Проверка отображения страницы цитат")
    @Description("Можно отобразить полный список цитат, нажав на кнопку \"Цитаты\".")
    public void checkDisplayingQuotes() {
        DataHelper validCredentials = DataHelper.validCredentials();
        loginSteps.login(validCredentials);
        mainPage.verifyMainPageWithShortNews();
        mainPage.clickQuotesButton();
        quotesPage.checkQuotesPage();
    }

    @Test
    @DisplayName("Разворачивание карточек цитат")
    @Description("Откроется карточка с дополнительным текстом цитаты.")
    public void openFullTextQuotes(){
        DataHelper validCredentials = DataHelper.validCredentials();
        loginSteps.login(validCredentials);
        mainPage.verifyMainPageWithShortNews();
        mainPage.clickQuotesButton();
        quotesPage.checkQuotesPage();
        quotesPage.fullCitations();
    }
}
