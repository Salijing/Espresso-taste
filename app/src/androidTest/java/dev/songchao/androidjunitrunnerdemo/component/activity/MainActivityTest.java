package dev.songchao.androidjunitrunnerdemo.component.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

import dev.songchao.androidjunitrunnerdemo.R;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by songchao on 2016/6/30.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMainActivity;

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        mMainActivity = getActivity();
    }

    @Test
    public void testOnCreate() throws Exception {
        TextView titleTextView = (TextView) mMainActivity.findViewById(R.id.content_textview);
        assertThat("显示不与目标一致", titleTextView.getText().toString(), equalTo("Hello World!"));
    }

    @Test
    public void testSetTitle() throws Exception {

    }

    @Test
    public void testInputText() throws Exception {

    }
}