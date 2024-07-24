package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
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

    private SimpleIdlingResource idlingResource;

    @Before
    public void setUp() {
        idlingResource = new SimpleIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }

    @Test
    public void loginTest1() {
        idlingResource.setIdleState(false);

        // Запустить задержку в фоновом потоке
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000); // 5 секунд задержки
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Установить состояние ожидания в true по окончании задержки
                idlingResource.setIdleState(true);
            }
        }).start();

        // Проверяем и вводим логин
        ViewInteraction textInputEditText = onView(withId(R.id.login_text_input_layout));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText("login2"), closeSoftKeyboard());

        // Проверяем и вводим пароль
        ViewInteraction textInputEditText2 = onView(withId(R.id.password_text_input_layout));
        textInputEditText2.check(matches(isDisplayed()));
        textInputEditText2.perform(replaceText("password2"), closeSoftKeyboard());

        // Нажимаем на кнопку входа
        ViewInteraction materialButton = onView(withId(R.id.enter_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());

        // Проверяем и нажимаем на кнопку авторизации = дальше я не оптимизировал
        onView(withId(R.id.authorization_image_button)).check(matches(isDisplayed()));
        onView(withId(R.id.authorization_image_button)).perform(click());

        // Проверяем и нажимаем на кнопку выхода
        onView(withText("Log out")).check(matches(isDisplayed()));
        onView(withText("Log out")).perform(click());

        // Проверяем, что мы снова на экране авторизации
        onView(withText("Authorization")).check(matches(isDisplayed()));
    }
}
