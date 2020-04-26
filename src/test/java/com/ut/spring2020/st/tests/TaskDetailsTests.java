package com.ut.spring2020.st.tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.ut.spring2020.st.framework.Browser;
import com.ut.spring2020.st.pages.TaskDetailsPage;
import com.ut.spring2020.st.pages.TasksDashboardPage;
import com.ut.spring2020.st.utilities.TestReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-25, Sat
 * webautomation
 **/

public class TaskDetailsTests extends BaseTest{
    Browser browser;

    @Parameters({"browserName", "baseUrl"})
    @BeforeClass(groups = {"web"})
    public void setUp(String browserName, String baseUrl) {
        browser = new Browser(browserName, baseUrl);
    }

    //View task
    @Test(groups = {"web"})
    public void viewTask() throws InterruptedException, IOException {
        TasksDashboardPage page;
        String titleLbl;
        String taskNamePrefix="Automation testing task - ";
        String taskStatus="";
        WebElement taskElement=null;
        String strTaskName=null;

        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksDashboardPage(browser));
        page = (TasksDashboardPage)browser.getCurrentPage();

        // Step 1. Load the dashboard page
        boolean pageTitle = true;
        titleLbl = page.getTitleLabel();
        if (titleLbl.equals("Task Dashboard")){
            TestReport.createTestNode("Task Dashboard loaded");
            Assert.assertTrue(true);
            TestReport.logTest(Status.PASS,"Task Dashboard loaded");
        }else {
            pageTitle = false;
        }

        // Step 2. Choose a task to click and navigate to view task page
        if(pageTitle){
            // Find the task that was added in the AddTaskTests suite
            List<WebElement> elems = browser.getDriver().findElements(By.xpath("//table[@class='table table-stripe']/tbody/tr/td[1]"));

            for(WebElement elem :elems){
                if(elem.getText().startsWith(taskNamePrefix)){ //Find the element that starts with task added by the AddTasksTestSuite, exclude datetime stamp
                    System.out.println("Found added task : " + elem.getText());
                    TestReport.logTest(Status.INFO,"Found added task : " + elem.getText());
                    taskElement = elem;
                    strTaskName = elem.getText();
                    break;
                }
            }
        }

        //Step 3. Navigate to the view task page
        if(strTaskName !=null){
            browser.getDriver().findElement(By.linkText(strTaskName)).click();
            TestReport.createTestNode("Navigate to view task page   ");
            Assert.assertTrue(true);
            TestReport.logTest(Status.INFO,"Clicked on the task : " + strTaskName);
        }

        browser.setCurrentPage(new TaskDetailsPage(browser)); // to do later remove the browser passed to page object;
        TaskDetailsPage taskPage = (TaskDetailsPage)browser.getCurrentPage();

        // Check if all buttons are present
        List<String> expectedButtons =  Arrays.asList( "View Tasks", "Return to Dashboard","Mark Complete","Edit Task","Delete Task");
        boolean allButtons = true;

        for (WebElement element : taskPage.getTaskDetailsBtns()) {
            if(expectedButtons.contains(element.getText())){
                System.out.println("Button : " + element.getText() + " is present");
            }else {
                System.out.println("Button : " + element.getText() + " is not present");
                allButtons = false;
            }
        }

        if(pageTitle && allButtons) {
            TestReport.createTestNode("View task page loaded");
            Assert.assertTrue(true);
            TestReport.logTest(Status.PASS,"View task page loaded");
        }


        MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
        TestReport.getExtentTest().info("View task page loaded ", mediaModel);

        Assert.assertTrue(allButtons && pageTitle);
    }

    @AfterClass(groups = {"web"})
    public void tearDown() {
        browser.quit();
    }
}
