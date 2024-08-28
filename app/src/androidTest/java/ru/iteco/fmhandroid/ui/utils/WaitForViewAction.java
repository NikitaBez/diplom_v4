package ru.iteco.fmhandroid.ui.utils;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.any;

public class WaitForViewAction implements ViewAction {

    private final int viewId;
    private final long millis;

    private WaitForViewAction(int viewId, long millis) {
        this.viewId = viewId;
        this.millis = millis;
    }

    @Override
    public Matcher<View> getConstraints() {
        return any(View.class);
    }

    @Override
    public String getDescription() {
        return "Wait for a view with id <" + viewId + "> to be displayed within " + millis + " milliseconds.";
    }

    @Override
    public void perform(UiController uiController, View view) {
        uiController.loopMainThreadUntilIdle();
        final long startTime = System.currentTimeMillis();
        final long endTime = startTime + millis;
        final Matcher<View> matchId = withId(viewId);
        final Matcher<View> matchDisplayed = isDisplayed();

        while (System.currentTimeMillis() < endTime) {
            for (View child : TreeIterables.breadthFirstViewTraversal(view.getRootView())) {
                if (matchId.matches(child) && matchDisplayed.matches(child)) {
                    return;
                }
            }
            uiController.loopMainThreadForAtLeast(50);
        }

        throw new PerformException.Builder()
                .withActionDescription(getDescription())
                .withViewDescription(HumanReadables.describe(view))
                .withCause(new TimeoutException())
                .build();
    }

    public static ViewAction waitDisplayed(int viewId, long millis) {
        return new WaitForViewAction(viewId, millis);
    }
}
