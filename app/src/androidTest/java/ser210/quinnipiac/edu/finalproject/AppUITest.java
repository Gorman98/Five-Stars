package ser210.quinnipiac.edu.finalproject;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppUITest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void appUITest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction editText = onView(
                allOf(withId(R.id.username),
                        childAtPosition(
                                allOf(withId(R.id.loginFragment),
                                        childAtPosition(
                                                withId(R.id.loginActivity),
                                                0)),
                                1),
                        isDisplayed()));
        editText.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.username),
                        childAtPosition(
                                allOf(withId(R.id.loginFragment),
                                        childAtPosition(
                                                withId(R.id.loginActivity),
                                                0)),
                                1),
                        isDisplayed()));
        editText2.perform(replaceText("Kyle"), closeSoftKeyboard());

        //pressBack();

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                allOf(withId(R.id.loginFragment),
                                        childAtPosition(
                                                withId(R.id.loginActivity),
                                                0)),
                                2),
                        isDisplayed()));
        editText3.perform(replaceText("1234"), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.password), withText("1234"),
                        childAtPosition(
                                allOf(withId(R.id.loginFragment),
                                        childAtPosition(
                                                withId(R.id.loginActivity),
                                                0)),
                                2),
                        isDisplayed()));
        editText4.perform(pressImeActionButton());

        ViewInteraction button = onView(
                allOf(withId(R.id.logInButton), withText("Log In"),
                        childAtPosition(
                                allOf(withId(R.id.loginFragment),
                                        childAtPosition(
                                                withId(R.id.loginActivity),
                                                0)),
                                3),
                        isDisplayed()));
        button.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3563582);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Friends"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.android.internal.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3591075);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3597086);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.title), withText("Share"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.android.internal.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        textView2.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.title), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.android.internal.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        textView3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3580174);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button2 = onView(
                allOf(withId(R.id.greenButton), withText("Green"),
                        childAtPosition(
                                allOf(withId(R.id.settingsFragment),
                                        childAtPosition(
                                                withId(R.id.settingsLayout),
                                                6)),
                                0),
                        isDisplayed()));
        button2.perform(click());

        pressBack();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3593886);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction textView4 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listGenres),
                        childAtPosition(
                                withId(R.id.mainFragment),
                                1)))
                .atPosition(0);
        textView4.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3595719);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.input),
                        childAtPosition(
                                allOf(withId(R.id.searchFragment),
                                        childAtPosition(
                                                withId(R.id.searchActivity),
                                                0)),
                                1),
                        isDisplayed()));
        editText5.perform(click());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.input),
                        childAtPosition(
                                allOf(withId(R.id.searchFragment),
                                        childAtPosition(
                                                withId(R.id.searchActivity),
                                                0)),
                                1),
                        isDisplayed()));
        editText6.perform(replaceText("hero"), closeSoftKeyboard());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.input), withText("hero"),
                        childAtPosition(
                                allOf(withId(R.id.searchFragment),
                                        childAtPosition(
                                                withId(R.id.searchActivity),
                                                0)),
                                1),
                        isDisplayed()));
        editText7.perform(pressImeActionButton());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.searchBttn), withText("Search"),
                        childAtPosition(
                                allOf(withId(R.id.searchFragment),
                                        childAtPosition(
                                                withId(R.id.searchActivity),
                                                0)),
                                2),
                        isDisplayed()));
        button3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3578373);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.review),
                        childAtPosition(
                                allOf(withId(R.id.reviewFragment),
                                        childAtPosition(
                                                withId(R.id.reviewActivity),
                                                0)),
                                2),
                        isDisplayed()));
        editText8.perform(click());

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.review),
                        childAtPosition(
                                allOf(withId(R.id.reviewFragment),
                                        childAtPosition(
                                                withId(R.id.reviewActivity),
                                                0)),
                                2),
                        isDisplayed()));
        editText9.perform(click());

        ViewInteraction editText10 = onView(
                allOf(withId(R.id.review),
                        childAtPosition(
                                allOf(withId(R.id.reviewFragment),
                                        childAtPosition(
                                                withId(R.id.reviewActivity),
                                                0)),
                                2),
                        isDisplayed()));
        editText10.perform(replaceText("It was good"), closeSoftKeyboard());

        ViewInteraction editText11 = onView(
                allOf(withId(R.id.review), withText("It was good"),
                        childAtPosition(
                                allOf(withId(R.id.reviewFragment),
                                        childAtPosition(
                                                withId(R.id.reviewActivity),
                                                0)),
                                2),
                        isDisplayed()));
        editText11.perform(pressImeActionButton());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.submitReview), withText("Submit"),
                        childAtPosition(
                                allOf(withId(R.id.reviewFragment),
                                        childAtPosition(
                                                withId(R.id.reviewActivity),
                                                0)),
                                4),
                        isDisplayed()));
        button4.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3549493);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction textView5 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listGenres),
                        childAtPosition(
                                withId(R.id.mainFragment),
                                1)))
                .atPosition(1);
        textView5.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3593572);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3597992);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction textView6 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listGenres),
                        childAtPosition(
                                withId(R.id.mainFragment),
                                1)))
                .atPosition(2);
        textView6.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3596032);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3598244);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction textView7 = onData(anything())
                .inAdapterView(allOf(withId(R.id.listGenres),
                        childAtPosition(
                                withId(R.id.mainFragment),
                                1)))
                .atPosition(3);
        textView7.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3596031);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3598741);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction textView8 = onView(
                allOf(withId(android.R.id.title), withText("Profile"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.android.internal.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        textView8.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3593396);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

    }

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
}
