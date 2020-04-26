package com.ut.spring2020.st.tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.ut.spring2020.st.framework.Browser;
import com.ut.spring2020.st.pages.AddTaskPage;
import com.ut.spring2020.st.pages.EditTaskPage;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-26, Sun
 * webautomation
 **/

public class EditTaskTests extends BaseTest{
    Browser browser;
    String taskName;
    String taskStatus;

    @Parameters({"browserName", "baseUrl"})
    @BeforeClass(groups = {"web"})
    public void setUp(String browserName, String baseUrl) {
        browser = new Browser(browserName, baseUrl);
        browser.navigateToBaseUrl();
        taskName ="";
        taskStatus ="";
    }

    /* - Test cases -
    * 1. Create a task
    * 2. Edit the status
    * 3. Mark as complete
    * 4. Delete
    * */
    @Test(groups = {"web"})
    public void addTask() throws InterruptedException, IOException {
        TasksDashboardPage page;
        String titleLbl;

        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksDashboardPage(browser)); // to do later remove the browser passed to page object
        page = (TasksDashboardPage)browser.getCurrentPage();

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


        browser.setCurrentPage(new TasksDashboardPage(browser)); // to do later remove the browser passed to page object
        page = (TasksDashboardPage)browser.getCurrentPage();

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

    @Test(groups = {"web"})
    public void editTask() throws InterruptedException, IOException {
        TasksDashboardPage page;
        String titleLbl;
        String viewTaskUrl=null;
        boolean editTaskComplete = false;

        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksDashboardPage(browser)); // to do later remove the browser passed to page object
        page = (TasksDashboardPage)browser.getCurrentPage();

        // 1. Load dashboard
        boolean pageTitle = true;
        titleLbl = page.getTitleLabel();
        if (titleLbl.equals("Task Dashboard")){
            TestReport.createTestNode("Task Dashboard loaded");
            Assert.assertTrue(true);
            TestReport.logTest(Status.PASS,"Task Dashboard loaded");
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Task dashboard page", mediaModel);
        }else {
            pageTitle = false;
        }

        //2. Click on the task to go to view task
        if(taskName !=null){
            WebElement elem = browser.getDriver().findElement(By.linkText(taskName));
            viewTaskUrl = elem.getAttribute("href");
            browser.getDriver().findElement(By.linkText(taskName)).click();
            TestReport.logTest(Status.INFO,"Clicked on the task : " + taskName);
        }

        //3. Verify that we are on the view task page
        if(browser.getDriver().getCurrentUrl().equals(viewTaskUrl)){
            TestReport.createTestNode("View task loaded");
            Assert.assertTrue(true);
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("View task page", mediaModel);
        }

       //4. Click on edit task button
        browser.setCurrentPage(new TaskDetailsPage(browser)); // to do later remove the browser passed to page object
        TaskDetailsPage taskDetailsPg = (TaskDetailsPage)browser.getCurrentPage();
        taskDetailsPg.getEditTaskBtn().click();
        TestReport.logTest(Status.INFO,"Clicked on edit task button");
        taskName = "Edited - " + taskName;
        taskStatus = "In progress";

        //5. Populate the modified values in edit screen
        browser.setCurrentPage(new EditTaskPage(browser)); // to do later remove the browser passed to page object
        EditTaskPage editTaskPg = (EditTaskPage)browser.getCurrentPage();
        if(editTaskPg.getTitleLabel().getText().equals("Edit Task")){
            editTaskPg.getTaskNameTxtBox().clear();
            editTaskPg.getTaskStatusTxtBox().clear();
            editTaskPg.getTaskNameTxtBox().sendKeys(taskName);
            editTaskPg.getTaskStatusTxtBox().sendKeys(taskStatus);
            editTaskPg.getUpdateTaskBtn().click();
            StringBuilder sb = new StringBuilder();
            sb.append("Task Name : ");
            sb.append(taskName);
            sb.append("\nStatus :");
            sb.append(taskStatus);
            TestReport.createTestNode("Edited task with details",sb.toString());
            Assert.assertTrue(true);
            TestReport.logTest(Status.INFO,sb.toString());
            TestReport.logTest(Status.INFO,"Update task button clicked");
            browser.setCurrentPage(new TaskDetailsPage(browser)); // to do later remove the browser passed to page object
            taskDetailsPg = (TaskDetailsPage)browser.getCurrentPage();
            if(editTaskPg.getTitleLabel().getText().equals("Task Details")) {
                MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
                TestReport.getExtentTest().info("Update results screen", mediaModel);
            }
            if(browser.getDriver().getCurrentUrl().equals(viewTaskUrl)){
                TestReport.createTestNode("Edit task completed");
                Assert.assertTrue(true);
                editTaskComplete = true;
            }
        }
        Assert.assertTrue(editTaskComplete);
    }

    @Test(groups = {"web"})
    public void markTaskAsComplete() throws InterruptedException, IOException {
        TasksDashboardPage page;
        String titleLbl;
        String viewTaskUrl=null;
        boolean markasComplete = false;

        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksDashboardPage(browser));
        page = (TasksDashboardPage)browser.getCurrentPage();

        // 1. Load dashboard
        boolean pageTitle = true;
        titleLbl = page.getTitleLabel();
        if (titleLbl.equals("Task Dashboard")){
            TestReport.createTestNode("Task Dashboard loaded");
            Assert.assertTrue(true);
            TestReport.logTest(Status.PASS,"Task Dashboard loaded");
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Task dashboard page", mediaModel);
        }else {
            pageTitle = false;
        }

        //2. Click on the task to go to view task
        if(taskName !=null){
            WebElement elem = browser.getDriver().findElement(By.linkText(taskName));
            viewTaskUrl = elem.getAttribute("href");
            browser.getDriver().findElement(By.linkText(taskName)).click();
            TestReport.logTest(Status.INFO,"Clicked on the task : " + taskName);
        }

        //3. Verify that we are on the view task page
        if(browser.getDriver().getCurrentUrl().equals(viewTaskUrl)){
            TestReport.createTestNode("View task loaded");
            Assert.assertTrue(true);
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("View task page", mediaModel);
        }

        //4. Click on mark as complete button
        browser.setCurrentPage(new TaskDetailsPage(browser));
        TaskDetailsPage taskDetailsPg = (TaskDetailsPage)browser.getCurrentPage();
        taskDetailsPg.getMarkCompleteBtn().click();
        TestReport.logTest(Status.INFO,"Clicked on mark as complete button");

        browser.setCurrentPage(new TasksDashboardPage(browser));
        page = (TasksDashboardPage)browser.getCurrentPage();
        titleLbl = page.getTitleLabel();
        if (titleLbl.equals("Task Dashboard")){
            TestReport.createTestNode("Task Dashboard loaded after mark as complete");
            Assert.assertTrue(true);
            TestReport.logTest(Status.PASS,"Task Dashboard loaded after mark as complete button clic");
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Task dashboard page after mark as complete", mediaModel);
            // TODO verify if the status is updated as complete
            markasComplete = true;
        }else {
            markasComplete = false;
        }
        Assert.assertTrue(pageTitle && markasComplete);
    }

    @Test(groups = {"web"})
    public void deleteTask() throws InterruptedException, IOException {
        TasksDashboardPage page;
        String titleLbl;
        String viewTaskUrl=null;
        boolean deleteTask = false;

        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksDashboardPage(browser));
        page = (TasksDashboardPage)browser.getCurrentPage();

        // 1. Load dashboard
        boolean pageTitle = true;
        titleLbl = page.getTitleLabel();
        if (titleLbl.equals("Task Dashboard")){
            TestReport.createTestNode("Task Dashboard loaded");
            Assert.assertTrue(true);
            TestReport.logTest(Status.PASS,"Task Dashboard loaded");
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Task dashboard page", mediaModel);
        }else {
            pageTitle = false;
        }

        //2. Click on the task to go to view task
        if(taskName !=null){
            WebElement elem = browser.getDriver().findElement(By.linkText(taskName));
            viewTaskUrl = elem.getAttribute("href");
            browser.getDriver().findElement(By.linkText(taskName)).click();
            TestReport.logTest(Status.INFO,"Clicked on the task : " + taskName);
        }

        //3. Verify that we are on the view task page
        if(browser.getDriver().getCurrentUrl().equals(viewTaskUrl)){
            TestReport.createTestNode("View task loaded");
            Assert.assertTrue(true);
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("View task page", mediaModel);
        }

        //4. Click on delete button
        browser.setCurrentPage(new TaskDetailsPage(browser));
        TaskDetailsPage taskDetailsPg = (TaskDetailsPage)browser.getCurrentPage();
        taskDetailsPg.getDeleteTaskBtn().click();
        TestReport.logTest(Status.INFO,"Clicked on delete button");

        browser.setCurrentPage(new TasksDashboardPage(browser));
        page = (TasksDashboardPage)browser.getCurrentPage();
        titleLbl = page.getTitleLabel();
        if (titleLbl.equals("Task Dashboard")){
            TestReport.createTestNode("Task Dashboard loaded after delete complete");
            Assert.assertTrue(true);
            TestReport.logTest(Status.PASS,"Task Dashboard loaded after delete button click");
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Task dashboard page after delete complete", mediaModel);
            // TODO verify that the task is not in the list
            deleteTask = true;
        }else {
            deleteTask = false;
        }
        Assert.assertTrue(pageTitle && deleteTask);
    }

    @AfterClass(groups = {"web"})
    public void tearDown() {
        browser.quit();
    }
}

