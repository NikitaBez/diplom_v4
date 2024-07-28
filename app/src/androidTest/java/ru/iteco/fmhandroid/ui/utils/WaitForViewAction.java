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

    public WaitForViewAction(int viewId, long millis) {
        this.viewId = viewId;
        this.millis = millis;
    }

    @Override
    public Matcher<View> getConstraints() {
        return any(View.class);
    }

    @Override
    public String getDescription() {
        return "wait for a specific view with id <" + viewId + "> to be displayed during " + millis + " millis.";
    }

    @Override
    public void perform(UiController uiController, View view) {
        uiController.loopMainThreadUntilIdle();
        final long startTime = System.currentTimeMillis();
        final long endTime = startTime + millis;
        final Matcher<View> matchId = withId(viewId);
        final Matcher<View> matchDisplayed = isDisplayed();

        do {
            for (View child : TreeIterables.breadthFirstViewTraversal(view.getRootView())) {
                if (matchId.matches(child) && matchDisplayed.matches(child)) {
                    return;
                }
            }

            uiController.loopMainThreadForAtLeast(50);
        } while (System.currentTimeMillis() < endTime);

        // timeout happens
        throw new PerformException.Builder()
                .withActionDescription(this.getDescription())
                .withViewDescription(HumanReadables.describe(view))
                .withCause(new TimeoutException())
                .build();
    }

    public static ViewAction waitDisplayed(final int viewId, final long millis) {
        return new WaitForViewAction(viewId, millis);
    }
}
