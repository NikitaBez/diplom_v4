package ru.iteco.fmhandroid.ui.tests;

import android.view.View;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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

public class AboutTest {

    private LoginSteps loginSteps;
    private MainPage mainPage;
    private NewsPage newsPage;
    private Logged logged;
    private QuotesPage quotesPage;
    private AboutPage aboutPage;
    private View decorView;
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
        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
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
    @DisplayName("Displaying the application version")
    @Description("You can view the application version")
    public void checkAppVersion() {
        loginAndVerifyHomePage();
        navigateToAboutPage();
        aboutPage.checkVersionOfApp();
        aboutPage.clickBackButton();
    }

    @Test
    @DisplayName("Link to privacy policy")
    @Description("A web page with the privacy policy will open")
    public void checkPrivacyPolicyLink() {
        loginAndVerifyHomePage();
        navigateToAboutPage();
        aboutPage.clickPrivacyPolicy();
        aboutPage.checkForIntendedToOpenPrivacyPolicy();
    }

    @Test
    @DisplayName("Link to terms of use")
    @Description("A web page with the terms of use will open")
    public void checkTermsOfUseLink() {
        loginAndVerifyHomePage();
        navigateToAboutPage();
        aboutPage.clickTermsOfUse();
        aboutPage.checkForIntendedToOpenTermsOfUse();
    }

    @Test
    @DisplayName("Copyright Check")
    @Description("Displaying the copyright owner, checking the operation of the \"Return\" button")
    public void copyrightCheck() {
        loginAndVerifyHomePage();
        navigateToAboutPage();
        aboutPage.copyrightCheck();
        aboutPage.clickBackButton();
    }

}
