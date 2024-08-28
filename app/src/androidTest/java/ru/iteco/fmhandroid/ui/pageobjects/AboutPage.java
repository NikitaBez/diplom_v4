package ru.iteco.fmhandroid.ui.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

import androidx.test.espresso.intent.matcher.IntentMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.utils.WaitForViewAction;

public class AboutPage {

    public void checkVersionOfApp() {
        Allure.step("Проверка отображения версии приложения");
        onView(withId(R.id.about_version_value_text_view)).check(matches(isDisplayed()));
    }

    public void clickBackButton() {
        Allure.step("Тап по кнопке 'Назад'");
        onView(withId(R.id.about_back_image_button)).perform(click());
    }

    public void clickPrivacyPolicy() {
        Allure.step("Тап по кнопке 'Политика конфиденциальности'");
        onView(withId(R.id.about_privacy_policy_value_text_view)).perform(click());
    }

    public void clickTermsOfUse() {
        Allure.step("Тап по кнопке 'Условия использования'");
        onView(withId(R.id.about_terms_of_use_value_text_view)).perform(click());
    }

    public void waitForElement(int viewId, long timeout) {
        Allure.step("Ожидание элемента '" + getElementDescription(viewId) + "' в течение " + timeout + " мс");
        onView(isRoot()).perform(WaitForViewAction.waitDisplayed(viewId, timeout));
    }

    public void checkForIntendedToOpenPrivacyPolicy() {
        Allure.step("Проверка перехода по ссылке на политику конфиденциальности");
        intended(allOf(
                IntentMatchers.hasAction(Intent.ACTION_VIEW),
                IntentMatchers.hasData(DataHelper.VALUE_LINK_OF_PRIVACY_POLICY)
        ));
    }

    public void checkForIntendedToOpenTermsOfUse() {
        Allure.step("Проверка перехода по ссылке на условия использования");
        intended(allOf(
                IntentMatchers.hasAction(Intent.ACTION_VIEW),
                IntentMatchers.hasData(DataHelper.VALUE_LINK_OF_TERMS_OF_USE)
        ));
    }

    public void copyrightCheck() {
        Allure.step("Проверка отображения информации о компании");
        onView(withId(R.id.about_company_info_label_text_view)).check(matches(isDisplayed()));
    }

    private String getElementDescription(int viewId) {
        switch (viewId) {
            case R.id.about_back_image_button:
                return "Назад";
            case R.id.about_privacy_policy_value_text_view:
                return "Политика конфиденциальности";
            case R.id.about_terms_of_use_value_text_view:
                return "Условия использования";
            case R.id.about_version_value_text_view:
                return "Версия приложения";
            case R.id.about_company_info_label_text_view:
                return "Информация о компании";
            default:
                return "Неизвестный элемент";
        }
    }
}
