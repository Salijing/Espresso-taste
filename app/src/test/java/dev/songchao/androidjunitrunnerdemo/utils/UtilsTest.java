package dev.songchao.androidjunitrunnerdemo.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Suite;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by songchao on 2016/6/30.
 */
@RunWith(JUnit4.class)
public class UtilsTest {

    private Utils mUtils;

    @Before//测试开始执行
    public void setUp() throws Exception {
        mUtils = new Utils();
    }

    @Test(timeout = 1000)//标识测试  超时
    public void testPlus() throws Exception {
        assertThat("结果不一致", mUtils.plus(1, 1), is(3));
//        Thread.sleep(1000);
    }

    @Test(expected = NullPointerException.class)//标识测试 异常
    public void testSubstraction() throws Exception {
        assertThat("结果不一致", mUtils.substraction(1, 1), anyOf(is(1), is(2), is(0)));
        throw new NullPointerException();
//        throw new ClassNotFoundException();
    }

    @After//测试结束之后执行
    public void end() {

    }
}