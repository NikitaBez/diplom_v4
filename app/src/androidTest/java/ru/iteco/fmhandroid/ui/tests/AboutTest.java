package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.pageobjects.AboutPage;
import ru.iteco.fmhandroid.ui.pageobjects.MainPage;
import ru.iteco.fmhandroid.ui.pageobjects.NewsPage;
import ru.iteco.fmhandroid.ui.pageobjects.QuotesPage;
import ru.iteco.fmhandroid.ui.steps.LoginSteps;
import ru.iteco.fmhandroid.ui.utils.Logged;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutTest {

    private LoginSteps loginSteps;
    private MainPage mainPage;
    private NewsPage newsPage;
    private Logged logged;
    private QuotesPage quotesPage;
    private AboutPage aboutPage;
    private DataHelper validCredentials;

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
        validCredentials = DataHelper.validCredentials();
        Intents.init();
        logged.ensureLoggedOut();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    private void loginAndVerifyHomePage() {
        loginSteps.login(validCredentials);
        mainPage.verifyMainPageWithShortNews();
    }

    private void navigateToAboutPage() {
        mainPage.clickHamburgerAndAbout();
    }

    @Test
    @DisplayName("Отображение версии приложения")
    @Description("Вы можете просмотреть версию приложения")
    public void checkAppVersion() {
        loginAndVerifyHomePage();
        navigateToAboutPage();
        aboutPage.checkVersionOfApp();
        aboutPage.clickBackButton();
    }

    @Test
    @DisplayName("Ссылка на политику конфиденциальности")
    @Description("Откроется веб-страница с политикой конфиденциальности")
    public void checkPrivacyPolicyLink() {
        loginAndVerifyHomePage();
        navigateToAboutPage();
        aboutPage.clickPrivacyPolicy();
        aboutPage.checkForIntendedToOpenPrivacyPolicy();
    }

    @Test
    @DisplayName("Ссылка на условия использования")
    @Description("Откроется веб-страница с условиями использования")
    public void checkTermsOfUseLink() {
        loginAndVerifyHomePage();
        navigateToAboutPage();
        aboutPage.clickTermsOfUse();
        aboutPage.checkForIntendedToOpenTermsOfUse();
    }

    @Test
    @DisplayName("Проверка авторских прав")
    @Description("Отображение владельца авторских прав, проверка работы кнопки \"Назад\"")
    public void copyrightCheck() {
        loginAndVerifyHomePage();
        navigateToAboutPage();
        aboutPage.copyrightCheck();
        aboutPage.clickBackButton();
    }
}
