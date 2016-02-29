package com.example.wavecontrol;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.example.wavecontrol.WaveControlTopAppActivityTest \
 * com.example.wavecontrol.tests/android.test.InstrumentationTestRunner
 */
public class WaveControlTopAppActivityTest extends ActivityInstrumentationTestCase2<WaveControlTopAppActivity> {

    public WaveControlTopAppActivityTest() {
        super("com.example.wavecontrol", WaveControlTopAppActivity.class);
    }

}
