package com.ut.spring2020.st.tests;

import com.ut.spring2020.st.utilities.TestReport;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-22, Wed
 * webautomation
 **/

public class BaseTest {
    @BeforeTest(groups = {"web"})
    public void beforeSuite()
    {
        TestReport.init();
    }
    @BeforeMethod(groups = {"web"})
    public void beforeEachTestCase(Method method)
    {
        String className = this.getClass().getSimpleName();
        TestReport.createTestCase(className + "-" + method.getName());
    }
    @AfterMethod(groups = {"web"})
    public void afterEachTestCase(ITestResult result)
    {
        TestReport.addTestResult(result);
    }

    @AfterTest(groups = {"web"})
    public void afterSuite()
    {
        TestReport.flush();
    }

}
