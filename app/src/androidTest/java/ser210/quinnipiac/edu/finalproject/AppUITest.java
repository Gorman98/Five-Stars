package ser210.quinnipiac.edu.finalproject;


import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppUITest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void appUITest() {
        // Log In Activity
        // type the text
        Espresso.onView(withId(R.id.username)).perform(typeText("Kyle"));
        Espresso.onView(withId(R.id.password)).perform(typeText("1234"));
        // close soft keyboard
        Espresso.closeSoftKeyboard();
        // perform button click
        Espresso.onView(withId(R.id.logInButton)).perform(click());

        // Main Activity
        // Clicks on Anime
        Espresso.onData(hasToString(startsWith("Anime"))).perform(click());

        // Search Activity
        // click on edit text
        Espresso.onView(withId(R.id.input)).perform(typeText("Naruto"));
        Espresso.closeSoftKeyboard();
        // click button
        Espresso.onView(withId(R.id.searchBttn)).perform(click());

        // Review Activity
        // Review media and submit
        Espresso.onView(withId(R.id.review)).perform(typeText("It was good"));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.submitReview)).perform(click());
    }

}
