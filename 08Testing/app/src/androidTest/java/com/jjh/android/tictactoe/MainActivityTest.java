package com.jjh.android.tictactoe;

import android.content.Intent;
import android.view.View;

import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickTopRightButton() {
        MainActivity activityUnderTest = this.activityRule.getActivity();
        Board board = activityUnderTest.board;
        Player player = board.getHumanPlayer();
        Counter counter = player.getCounter();
        onView(withId(R.id.button0)).perform(click());
        // Check the screen update
        onView(withId(R.id.button0)).check(matches(withText(counter.getLabel())));
    }

    @Test
    public void clickResetButtonIsDisabled() {
        onView(withId(R.id.button9)).check(matches(not(isEnabled())));
    }
}