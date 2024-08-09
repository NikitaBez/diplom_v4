package ru.iteco.fmhandroid.ui.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class QuotesPage {
    //верификация открытия страницы цитат
    public void checkQuotesPage() {
        onView(withId(R.id.our_mission_title_text_view))
                .check(matches(isDisplayed()));
    }
}