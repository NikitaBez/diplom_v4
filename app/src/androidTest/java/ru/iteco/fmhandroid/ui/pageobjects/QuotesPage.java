package ru.iteco.fmhandroid.ui.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.ui.utils.AppManager;
import ru.iteco.fmhandroid.R;

public class QuotesPage {

    public void checkQuotesPage() {
        onView(withId(R.id.our_mission_title_text_view))
                .check(matches(isDisplayed()));
    }

    public void fullCitations() {
        String text = "Все сотрудники хосписа - это адвокаты пациента, его прав и потребностей. Поиск путей решения различных задач - это и есть хосписный индивидуальный подход к паллиативной помощи.";

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.our_mission_item_list_recycler_view),
                        AppManager.childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView2 = onView(allOf(withId(R.id.our_mission_item_description_text_view), withText(text)));
        textView2.check(matches(withText(text)));
    }
}
