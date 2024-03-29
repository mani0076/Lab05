package com.example.lab05;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.lab05.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

    @LargeTest
    @RunWith(AndroidJUnit4.class)
    public class MainActivityTest {

        @Rule
        public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
                new ActivityScenarioRule<>(MainActivity.class);

        @Test
        public void mainActivityTest() {
            ViewInteraction appCompatEditText = onView(withId(R.id.editText));
            appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

            ViewInteraction materialButton = onView(withId(R.id.button));
            materialButton.perform(click());

            ViewInteraction textView = onView(withId(R.id.textView));
            textView.check(matches(withText("You shall not pass!")));
        }


        public void testFindMissingUppercase(){
            // find the view
            ViewInteraction appCompatEditText = onView(withId(R.id.editText));
            // type in password123#$*
            appCompatEditText.perform(replaceText("password123#$*"));

            // find the button
            ViewInteraction materialButton = onView(withId(R.id.button));
            //click the button
            materialButton.perform(click());

            // find the text view
            ViewInteraction textView = onView(withId(R.id.textView));
            // check the text
            textView.check(matches(withText("You shall not pass!")));
        }


        public void testFindMissingLowercase(){
            // find the view
            ViewInteraction appCompatEditText = onView(withId(R.id.editText));
            // type in password123#$*
            appCompatEditText.perform(replaceText("KOMAL123#$*"));

            // find the button
            ViewInteraction materialButton = onView(withId(R.id.button));
            //click the button
            materialButton.perform(click());

            // find the text view
            ViewInteraction textView = onView(withId(R.id.textView));
            // check the text
            textView.check(matches(withText("You shall not pass!")));
        }


        public void testFindMissingDigit(){
            // find the view
            ViewInteraction appCompatEditText = onView(withId(R.id.editText));
            // type in password123#$*
            appCompatEditText.perform(replaceText("Komal$$"));

            // find the button
            ViewInteraction materialButton = onView(withId(R.id.button));
            //click the button
            materialButton.perform(click());

            // find the text view
            ViewInteraction textView = onView(withId(R.id.textView));
            // check the text
            textView.check(matches(withText("You shall not pass!")));
        }


        public void testFindMissingSpecial(){
            // find the view
            ViewInteraction appCompatEditText = onView(withId(R.id.editText));
            // type in password123#$*
            appCompatEditText.perform(replaceText("Komal0029"));

            // find the button
            ViewInteraction materialButton = onView(withId(R.id.button));
            //click the button
            materialButton.perform(click());

            // find the text view
            ViewInteraction textView = onView(withId(R.id.textView));
            // check the text
            textView.check(matches(withText("You shall not pass!")));
        }


        public void testFindMeetingAllRequirements(){
            // find the view
            ViewInteraction appCompatEditText = onView(withId(R.id.editText));
            // type in password123#$*
            appCompatEditText.perform(replaceText("Komal0029$$"));

            // find the button
            ViewInteraction materialButton = onView(withId(R.id.button));
            //click the button
            materialButton.perform(click());

            // find the text view
            ViewInteraction textView = onView(withId(R.id.textView));
            // check the text
            textView.check(matches(withText("Your password meets the requirements")));
        }

        private static Matcher<View> childAtPosition(
                final Matcher<View> parentMatcher, final int position) {

            return new TypeSafeMatcher<View>() {
                @Override
                public void describeTo(Description description) {
                    description.appendText("Child at position " + position + " in parent ");
                    parentMatcher.describeTo(description);
                }


                public boolean matchesSafely(View view) {
                    ViewParent parent = view.getParent();
                    return parent instanceof ViewGroup && parentMatcher.matches(parent)
                            && view.equals(((ViewGroup) parent).getChildAt(position));
                }
            };
        }
    }

