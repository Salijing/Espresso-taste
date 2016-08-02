package dev.songchao.androidjunitrunnerdemo.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by songchao on 2016/6/30.
 */
@RunWith(Parameterized.class)
public class StringUtilsTest {

    private StringUtils mStringUtils;

    private String mValue;

    public StringUtilsTest(String value) {
        mValue = value;
    }

    @Before
    public void setUp() throws Exception {
        mStringUtils = new StringUtils();
    }

    /**
     * 这里的返回至少是二维数组
     *
     * @return
     */
    @Parameterized.Parameters
    public static List<String[]> getParams() {
        return Arrays.asList(new String[][]{{"A"},
                {"B"}, {"C"}, {"D"}});
    }

    @Test
    public void testPrint() {
        System.out.println("value ->  " + mValue);
    }

    @Test
    public void testStringCount() throws Exception {
        assertEquals("字数一致", 10, mStringUtils.stringCount("123456789"));
    }
}