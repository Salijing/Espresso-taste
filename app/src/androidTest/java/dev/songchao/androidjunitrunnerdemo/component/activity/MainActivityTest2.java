package dev.songchao.androidjunitrunnerdemo.component.activity;

import android.content.Context;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.base.DefaultFailureHandler;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dev.songchao.androidjunitrunnerdemo.R;
import dev.songchao.androidjunitrunnerdemo.model.bean.AListBean;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.setFailureHandler;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

/**
 * Created by songchao on 2016/6/30.
 */
public class MainActivityTest2 extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMainActivity;

    private IdlingResource.ResourceCallback mResourceCallback;
    private  TestIdlingResource mTestIdlingResource;

    public MainActivityTest2() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        Log.d("Sysout", "==========================================================================begin");
        mMainActivity = getActivity();
        ////分配异常处理者
        setFailureHandler(new CustomFailureHandler(mMainActivity));
//        mTestIdlingResource = new TestIdlingResource(mMainActivity);
//        mResourceCallback = new TestResourceCallback();
//        mTestIdlingResource.registerIdleTransitionCallback(mResourceCallback);
//        registerIdlingResources(mTestIdlingResource);
    }

    @Test
    public void testClick() {
        onView(ViewMatchers.withId(R.id.test_click)).perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.content_textview)).check(ViewAssertions.matches(ViewMatchers.withText("<!DOCTYPE html>")));
        onView(ViewMatchers.withId(R.id.content_edittext)).check(ViewAssertions.matches(ViewMatchers.withText("ABCD")));
        onView(ViewMatchers.withId(R.id.content_edittext)).perform(ViewActions.typeText("12334")).check(ViewAssertions.matches(
                ViewMatchers.withText("ABCD12334")
        ));
        closeSoftKeyboard();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testList() {
        onData(allOf(instanceOf(AListBean.class), getAMatcher("1826262626262"))).
                inAdapterView(ViewMatchers.withId(R.id.list_1)).atPosition(2);
    }

    private Matcher<Object> getAMatcher(final String phone) {
        return new BoundedMatcher<Object, AListBean>(AListBean.class) {
            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(AListBean item) {
                return item != null && item.getPhone().equals(phone);
            }
        };
    }

    private class CustomFailureHandler implements FailureHandler {
        private final FailureHandler delegate;

        public CustomFailureHandler(Context targetContext) {
            Log.d("Sysout", "init CustomFailureHandler");
            //如果只是看看异常的问题，这里可以在生成一个默认的处理者，出问题让它后续处理
            delegate = new DefaultFailureHandler(targetContext);
        }

        @Override
        public void handle(Throwable error, Matcher<View> viewMatcher) {
            Log.d("Sysout", "CustomFailureHandler handle -> " + error.getMessage());
            try {
                delegate.handle(error, viewMatcher);
            } catch (NoMatchingViewException e) {
                throw e;
            }
        }
    }

    private class TestIdlingResource implements IdlingResource {
        private ResourceCallback callback;
        private TextView contentTV;

        public TestIdlingResource(MainActivity mainActivity) {
            contentTV = (TextView) mainActivity.findViewById(R.id.content_textview);
        }

        @Override
        public String getName() {
            Log.d("Sysout", "IdlingResource getName-> " + TestIdlingResource.class.getName());
            return TestIdlingResource.class.getName();
        }

        @Override
        public boolean isIdleNow() {
            if (contentTV.getText().length() != 0) {
                Log.d("Sysout", "IdlingResource idlenow is true");
                callback.onTransitionToIdle();
                return true;
            } else {
                Log.d("Sysout", "IdlingResource idlenow is false");
                return false;
            }
        }

        @Override
        public void registerIdleTransitionCallback(IdlingResource.ResourceCallback callback) {
            Log.d("Sysout", "IdlingResource register IdleTransitionCallback");
            this.callback = callback;
        }
    }

    private class TestResourceCallback implements IdlingResource.ResourceCallback {
        @Override
        public void onTransitionToIdle() {
            Log.d("Sysout", "start transitionToIdle");
        }
    }

    @After
    public void testAfter() {
//        unregisterIdlingResources(mTestIdlingResource);
        Log.d("Sysout", "==========================================================================end");
    }
}