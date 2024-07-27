package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.opengl.GLException;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginTest1 {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void loginTest1() {

        String LOGIN = "login2";
        String PASSWORD = "password2";

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверяем и вводим логин
        ViewInteraction textInputlogin = onView(withHint("Login"));
        textInputlogin.check(matches(isDisplayed()));
        textInputlogin.perform(typeText(LOGIN), closeSoftKeyboard());

        // Проверяем и вводим пароль
        ViewInteraction textInputPassword = onView(withHint("Password"));
        textInputPassword.check(matches(isDisplayed()));
        textInputPassword.perform(typeText(PASSWORD), closeSoftKeyboard());

        // Нажимаем на кнопку входа
        ViewInteraction materialEnterButton = onView(withId(R.id.enter_button));
        materialEnterButton.check(matches(isDisplayed()));
        materialEnterButton.perform(click());

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Проверка открытия гравной страницы с блоком новостей
        ViewInteraction assertMainPage = onView(withId(R.id.all_news_text_view));
        assertMainPage.check(matches(isDisplayed()));
        assertMainPage.check(matches(withText("ALL NEWS")));

        // Разлогинивание - нажатие на меню разлогинивания
        ViewInteraction tapAuthorizationBtn = onView(withId(R.id.authorization_image_button));
        tapAuthorizationBtn.check(matches(isDisplayed()));
        tapAuthorizationBtn.perform(click());

        // Разлогинивание - нажатие на выход
        ViewInteraction logOffBtn = onView(withId(android.R.id.title));
        logOffBtn.check(matches(isDisplayed()));
        logOffBtn.perform(click());
    }
}
