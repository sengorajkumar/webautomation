package com.ut.spring2020.st.utilities;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-21, Tue
 * webautomation
 **/

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestReport {

    private static ExtentSparkReporter sparkReporter;
    private static ExtentReports extentReport;
    private static ExtentTest extentTest;
    private static String reportPath;
    // Init the report
    public static void init(){

        reportPath = System.getProperty("user.dir") + "/report/";

        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Web Application Automation");
        sparkReporter.config().setReportName("Web Application Automation Execution Report");
        sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
        extentReport = new ExtentReports();
        extentReport.attachReporter(sparkReporter);
        extentReport.setSystemInfo("Application", "To do web application");
        extentReport.setSystemInfo("Platform", System.getProperty("os.name"));
        extentReport.setSystemInfo("Environment", "Production");
    }

    public static void createTestCase(String name){
        extentTest = extentReport.createTest(name);
    }
    // Log each test result
    public static void addTestResult(ITestResult result){

        String methodName = result.getName();
        extentTest.createNode(methodName);
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(methodName + " – Test Case Failed", ExtentColor.RED));
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " – Test Case Failed", ExtentColor.RED));
            // Here you can add screenshot in the report for fail case
            extentTest.fail(methodName + " Test Step Failed");
        }
        if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " – Test Case Skipped", ExtentColor.ORANGE));
            extentTest.skip(methodName + " Test Step Skipped");
        }
        if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
            extentTest.pass(methodName + " Test Step Passed");
        }
    }

    //Save the report
    public static void flush(){
        extentReport.flush();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
        Date date = new Date();
        String filePathdate = dateFormat.format(date).toString();
        String actualReportPath = reportPath + "index.html";
        new File(actualReportPath).renameTo(new File(reportPath+ "webapp_automation_" + filePathdate + ".html"));
    }
}
