package dk.easj.anbo.calculatorplus;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    // https://developer.android.com/training/testing/ui-testing/espresso-testing
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("dk.easj.anbo.calculatorplus", appContext.getPackageName());

        onView(withId(R.id.mainNumber1EditText)).perform(typeText("4"));
        onView(withId(R.id.mainNumber2EditText)).perform(typeText("3"));
        onView(withId(R.id.mainCalculateButton)).perform(click());
        onView(withId(R.id.mainResultEditText)).check(matches(withText("7.0")));

        onView(withId(R.id.mainNumber1EditText)).perform(typeText("4")); // adds extra 4, so now it's 44
        onView(withId(R.id.mainNumber2EditText)).perform(clearText());
        onView(withId(R.id.mainCalculateButton)).perform(click(), closeSoftKeyboard());
        // notice closeSoftKeyboard()
        onView(withId(R.id.mainMessageTextView)).check(matches(withText("Not a number")));
    }
}
