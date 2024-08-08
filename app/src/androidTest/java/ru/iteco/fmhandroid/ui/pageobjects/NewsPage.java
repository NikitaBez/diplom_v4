package ru.iteco.fmhandroid.ui.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;

public class NewsPage {
    //верификация открытия страницы новостей
    public void verifyNewsPage() {
        onView(withId(R.id.all_news_cards_block_constraint_layout))
                .check(matches(isDisplayed()));
    }
}
