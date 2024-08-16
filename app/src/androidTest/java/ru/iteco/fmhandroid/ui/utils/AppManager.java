package ru.iteco.fmhandroid.ui.utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pageobjects.LoginPage;
import ru.iteco.fmhandroid.ui.pageobjects.MainPage;

public class AppManager {

    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();

    public static void waitElement(int viewId, long millis) {
        onView(isRoot()).perform(WaitForViewAction.waitDisplayed((viewId), millis));
    }

    public boolean isOnLoginPage() {
        try {
            waitElement(R.id.login_text_input_layout, 5000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOnNewsPage() {
        try {
            waitElement(R.id.all_news_text_view, 5000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                View parent = (View) view.getParent();
                // Проверяем, является ли родитель ViewGroup
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getCurrentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date();
        return timeFormat.format(date);
    }

    public static void inputText(Integer resourceId, String inputText) {
        onView(allOf(withId(resourceId)))
                .check(matches(isDisplayed()))
                .perform(replaceText(inputText), closeSoftKeyboard());
    }

    public static int getItemCountFromRecyclerView() {
        AtomicInteger count = new AtomicInteger();

        onView(withId(R.id.news_list_recycler_view))
                .check(matches(isDisplayed()))
                .perform(new RecyclerViewItemCountAssertion(count));

        return count.get();
    }

    private static class RecyclerViewItemCountAssertion implements ViewAction {
        private final AtomicInteger count;

        RecyclerViewItemCountAssertion(AtomicInteger count) {
            this.count = count;
        }

        @Override
        public Matcher<View> getConstraints() {
            return isAssignableFrom(RecyclerView.class);
        }

        @Override
        public String getDescription() {
            return "Get recycler view item count";
        }

        @Override
        public void perform(UiController uiController, View view) {
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            count.set(adapter.getItemCount());
        }
    }

    public static Matcher<View> withRecyclerViewItemResource(final int resourceId) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(View view) {
                if (view instanceof RecyclerView) {
                    RecyclerView recyclerView = (RecyclerView) view;
                    int itemCount = recyclerView.getAdapter().getItemCount();
                    for (int position = 0; position < itemCount; position++) {
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                        if (viewHolder != null && viewHolder.itemView.getId() != resourceId) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }

        };
    }
    public static ViewAction clickChildElementById(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(View.class), isDisplayed());
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
}

