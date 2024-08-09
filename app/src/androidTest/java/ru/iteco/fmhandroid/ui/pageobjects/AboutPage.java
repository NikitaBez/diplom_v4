package ru.iteco.fmhandroid.ui.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class AboutPage {
    //верификация открытия страницы About
    public void checkAboutPage() {
        onView(withId(R.id.about_version_value_text_view))
                .check(matches(isDisplayed()));
    }

    public void clickBackButton(){
        onView(withId(R.id.our_mission_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }
    }
}
