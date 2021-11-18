package com.example.maru;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.ui.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MareuInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void addMeeting() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab_Add),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.btn_hour),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.txt_hour),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.btn_duration),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.txt_duration),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText2.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.btn_room),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.txt_room),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(3);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction textInputEditText4 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.txt_subject),
                                0),
                        1),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("ddd"), closeSoftKeyboard());

        ViewInteraction textInputEditText5 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.txt_email),
                                0),
                        1),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("ww@xx.cc"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.addEmail),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.SaveButton), withText("Créer la réunion"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        materialButton3.perform(click());
    }

    @Test
    public void filterHour() {
        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("Plus d'options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.title), withText("Filtre par heure"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction overflowMenuButton3 = onView(
                allOf(withContentDescription("Plus d'options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton3.perform(click());

        ViewInteraction materialTextView3 = onView(
                allOf(withId(R.id.title), withText("Effacer le filtre"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView3.perform(click());
    }

    @Test
    public void filterRoom() {
        ViewInteraction overflowMenuButton2 = onView(
                allOf(withContentDescription("Plus d'options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton2.perform(click());

        ViewInteraction materialTextView2 = onView(
                allOf(withId(R.id.title), withText("Filtre par salle"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView2.perform(click());

        DataInteraction appCompatCheckedTextView3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(1);
        appCompatCheckedTextView3.perform(click());

        ViewInteraction overflowMenuButton3 = onView(
                allOf(withContentDescription("Plus d'options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton3.perform(click());

        ViewInteraction materialTextView3 = onView(
                allOf(withId(R.id.title), withText("Effacer le filtre"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView3.perform(click());
    }

    @Test
    public void deleteMeeting() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.delete_reunion),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerview),
                                        0),
                                6),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("Oui"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());
    }
}