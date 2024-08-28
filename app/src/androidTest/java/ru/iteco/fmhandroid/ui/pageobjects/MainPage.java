package ru.iteco.fmhandroid.ui.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.WaitForViewAction;

public class MainPage {

    public void verifyMainPageWithShortNews() {
        Allure.step("Проверка отображения основного экрана с текстом 'ALL NEWS'");
        onView(withId(R.id.all_news_text_view))
                .check(matches(isDisplayed()))
                .check(matches(withText("ALL NEWS")));
    }

    public void clickAuthorizationButton() {
        Allure.step("Тап по кнопке 'Авторизация'");
        onView(withId(R.id.authorization_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickLogOffButton() {
        Allure.step("Тап по кнопке 'Выход'");
        onView(withId(android.R.id.title))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void waitForElement(int viewId, long timeout) {
        Allure.step("Ожидание элемента '" + getElementDescription(viewId) + "' в течение " + timeout + " мс");
        onView(isRoot()).perform(WaitForViewAction.waitDisplayed(viewId, timeout));
    }

    public void autoLogout() {
        Allure.step("Автовыход из системы");
        clickAuthorizationButton();
        waitForElement(android.R.id.title, 5000);
        clickLogOffButton();
    }

    public void clickAllNewsButton() {
        Allure.step("Тап по кнопке 'Все новости'");
        onView(withId(R.id.all_news_text_view))
                .check(matches(isDisplayed()))
                .check(matches(withText("ALL NEWS")))
                .perform(click());
    }

    public void clickHamburgerAndNews() {
        Allure.step("Открытие меню и переход в 'Новости'");
        onView(withId(R.id.main_menu_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(allOf(withId(android.R.id.title), withText("News")))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickHamburgerAndAbout() {
        Allure.step("Открытие меню и переход в 'О приложении'");
        onView(withId(R.id.main_menu_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(allOf(withId(android.R.id.title), withText("About")))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickQuotesButton() {
        Allure.step("Тап по кнопке 'Наша миссия'");
        onView(withId(R.id.our_mission_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    private String getElementDescription(int viewId) {
        switch (viewId) {
            case R.id.authorization_image_button:
                return "Авторизация";
            case R.id.all_news_text_view:
                return "Все новости";
            case R.id.main_menu_image_button:
                return "Главное меню";
            case R.id.our_mission_image_button:
                return "Наша миссия";
            case android.R.id.title:
                return "Заголовок";
            default:
                return "Неизвестный элемент";
        }
    }
}
