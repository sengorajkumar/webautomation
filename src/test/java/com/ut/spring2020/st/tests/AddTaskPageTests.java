package com.ut.spring2020.st.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.ut.spring2020.st.framework.Browser;
import com.ut.spring2020.st.pages.AddTaskPage;
import com.ut.spring2020.st.pages.Page;
import com.ut.spring2020.st.pages.TasksListHomePage;
import com.ut.spring2020.st.utilities.TestReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-22, Wed
 * webautomation
 **/

public class AddTaskPageTests extends BaseTest{
    Browser browser;
    //Page page;

    //@Parameters({"browserName", "addTaskURL"})
    @Parameters({"browserName", "baseUrl"})
    @BeforeClass(groups = {"web"})
    public void setUp(String browserName, String baseUrl) {
        browser = new Browser(browserName, baseUrl);
        browser.navigateToBaseUrl();
        //browser.setCurrentPage(new AddTaskPage(browser)); // to do later remove the browser passed to page object;
        //page = (AddTaskPage)browser.getCurrentPage();
    }
    // Test case to check home page loading
    @Test(groups = {"web"})
    public void createTask() throws InterruptedException, IOException {
        //browser.navigateToBaseUrl();
        //browser.setCurrentPage(new AddTaskPage(browser)); // to do later remove the browser passed to page object;
        //page = (AddTaskPage)browser.getCurrentPage();
        TasksListHomePage page;
        String titleLbl;
        String taskName="";
        String taskStatus="";

        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksListHomePage(browser)); // to do later remove the browser passed to page object
        page = (TasksListHomePage)browser.getCurrentPage();

        // Check if page title is present
        boolean pageTitle = true;
        titleLbl = page.getTitleLabel();
        if (titleLbl.equals("Task Dashboard")){
            TestReport.createTestNode("Home page loaded");
            Assert.assertTrue(true);
            TestReport.logTest(Status.PASS,"Home page loaded");
            page.getAddTaskBtn().click();
        }else {
            pageTitle = false;
        }

        browser.setCurrentPage(new AddTaskPage(browser)); // to do later remove the browser passed to page object;
        AddTaskPage taskPage = (AddTaskPage)browser.getCurrentPage();

        // Check if all buttons are present
        List<String> expectedButtons =  Arrays.asList( "Add Task", "Return to Dashboard");
        boolean allButtons = true;
        for (WebElement element : taskPage.getAddTaskPageBtns()) {
            if(expectedButtons.contains(element.getText())){
                System.out.println("Button : " + element.getText() + " is present");
            }else {
                System.out.println("Button : " + element.getText() + " is not present");
                allButtons = false;
            }
        }
        if(pageTitle && allButtons) {
            TestReport.createTestNode("Add task page loaded");
            Assert.assertTrue(true);
            TestReport.logTest(Status.PASS,"Add task page loaded");
        }

        if(pageTitle && allButtons){
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
            Date date = new Date();
            taskName = "Automation testing task - " + dateFormat.format(date).toString();
            taskStatus = "Open";
            taskPage.gettaskNameTxtBox().sendKeys(taskName);
            taskPage.gettaskStatusTxtBox().sendKeys(taskStatus);
            taskPage.getAddTaskBtn().click();
            StringBuilder sb = new StringBuilder();
            sb.append("Task Name : ");
            sb.append(taskName);
            sb.append("\nStatus :");
            sb.append(taskStatus);
            TestReport.createTestNode("Add task button clicked",sb.toString());
            Assert.assertTrue(true);
            TestReport.logTest(Status.INFO,sb.toString());
            TestReport.logTest(Status.INFO,"Add task button clicked");
        }


        browser.setCurrentPage(new TasksListHomePage(browser)); // to do later remove the browser passed to page object
        page = (TasksListHomePage)browser.getCurrentPage();

        // Check if page title is present
        //boolean pageTitle = true;
        titleLbl = page.getTitleLabel();
        if (titleLbl.equals("Task Dashboard")){
            TestReport.logTest(Status.INFO,"Home page refreshed");
            List<WebElement> elems = browser.getDriver().findElements(By.xpath("//table[@class='table table-stripe']/tbody/tr/td[1]"));

            for(WebElement elem :elems){
                if(elem.getText().equals(taskName)){
                    System.out.println("Found added task : " + elem.getText());
                    TestReport.logTest(Status.INFO,"Found added task : " + elem.getText());
                    break;
                }
            }

            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Home page with newly added task ", mediaModel);

            TestReport.createTestNode("Task added");
            Assert.assertTrue(true);
        }else {
            pageTitle = false;
            TestReport.createTestNode("Task added");
            Assert.assertTrue(false);

        }
        Assert.assertTrue(allButtons && pageTitle);
    }


    @AfterClass(groups = {"web"})
    public void tearDown() {
        browser.quit();
    }

}
