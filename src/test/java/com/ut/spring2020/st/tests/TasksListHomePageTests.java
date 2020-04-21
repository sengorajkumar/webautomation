package com.ut.spring2020.st.tests;
/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-20, Mon
 * webautomation
 **/

import com.ut.spring2020.st.framework.Browser;
import com.ut.spring2020.st.pages.TasksListHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TasksListHomePageTests {

    Browser browser;

    TasksListHomePage page;

    @Parameters({"browserName", "baseUrl"})
    @BeforeClass(groups = {"web"})
    public void setUp(String browserName, String baseUrl) {
        browser = new Browser(browserName, baseUrl);
        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksListHomePage(browser)); // to do later remove the browser passed to page object;
        page = (TasksListHomePage)browser.getCurrentPage();
    }

    @Test
    public void testName() {
    }

    // Test case to check home page loading
    @Test(groups = {"web"})
    public void LoadHomePage() throws InterruptedException {

        browser.setCurrentPage(new TasksListHomePage(browser)); // to do later remove the browser passed to page object
        TasksListHomePage page = (TasksListHomePage)browser.getCurrentPage();

        // Check if page title is present
        boolean pageTitle = true;
        String titleLbl = page.getTitleLabel();
        if (titleLbl.equals("Task Dashboard")){
            System.out.println("Title : " + titleLbl + " is present");
        }else {
            pageTitle = false;
        }

        // Check if all buttons are present
        List<String> expectedButtons =  Arrays.asList( "Add Task", "View Tasks", "Create User","View Users");
        boolean allButtons = true;
        for (WebElement element : page.getHpButtons()) {
            if(expectedButtons.contains(element.getText())){
                System.out.println("Button : " + element.getText() + " is present");
            }else {
                System.out.println("Button : " + element.getText() + " is not present");
                allButtons = false;
            }
        }

        //Assert.assertEquals(page.getTitleLabel(), "Task Dashboard");
        Assert.assertTrue(allButtons && pageTitle);
    }

    // Click on View Tasks and verify
    @Test(groups = {"web"})
    public void viewTasks() throws InterruptedException {
        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksListHomePage(browser)); // to do later remove the browser passed to page object
        TasksListHomePage page = (TasksListHomePage)browser.getCurrentPage();

        page.getViewTasksBtn().click();

        WebDriverWait waitForResponse = new WebDriverWait(browser.getDriver(), 5);
        waitForResponse.until(ExpectedConditions.visibilityOfElementLocated(By.className("panel-title")));

        String expectedURL = "http://reactboot.cfapps.io/task";

        // Check if page title is present
        boolean pageTitle = true;
        String titleLbl = page.getTitleLabel();
        if (titleLbl.equals("All Tasks")){
            System.out.println("Title : " + titleLbl + " is present");
        }else {
            pageTitle = false;
        }

        Assert.assertTrue(expectedURL.equals(browser.getCurrentUrl()) && pageTitle);

    }

    // Click on Create User and verify
    @Test(groups = {"web"})
    public void createUser() throws InterruptedException {
        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksListHomePage(browser)); // to do later remove the browser passed to page object
        TasksListHomePage page = (TasksListHomePage)browser.getCurrentPage();

        page.getCreateUsrBtn().click();

        WebDriverWait waitForResponse = new WebDriverWait(browser.getDriver(), 5);
        waitForResponse.until(ExpectedConditions.visibilityOfElementLocated(By.className("panel-title")));

        String expectedURL = "http://reactboot.cfapps.io/create";

        // Check if page title is present
        boolean pageTitle = true;
        String titleLbl = page.getTitleLabel();
        if (titleLbl.equals("Create User")){
            System.out.println("Title : " + titleLbl + " is present");
        }else {
            pageTitle = false;
        }

        Assert.assertTrue(expectedURL.equals(browser.getCurrentUrl()) && pageTitle);

    }
    // Click on View User and verify
    @Test(groups = {"web"})
    public void viewUsers() throws InterruptedException {
        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksListHomePage(browser)); // to do later remove the browser passed to page object
        TasksListHomePage page = (TasksListHomePage)browser.getCurrentPage();

        page.getViewUsrBtn().click();

        WebDriverWait waitForResponse = new WebDriverWait(browser.getDriver(), 5);
        waitForResponse.until(ExpectedConditions.visibilityOfElementLocated(By.className("panel-title")));

        String expectedURL = "http://reactboot.cfapps.io/user";

        // Check if page title is present
        boolean pageTitle = true;
        String titleLbl = page.getTitleLabel();
        if (titleLbl.equals("Users")){
            System.out.println("Title : " + titleLbl + " is present");
        }else {
            pageTitle = false;
        }

        Assert.assertTrue(expectedURL.equals(browser.getCurrentUrl()) && pageTitle);
        //Assert.assertTrue(this.checkPageLoad(page.getViewUsrBtn(),"View User","http://reactboot.cfapps.io/user"));
    }

    private boolean checkPageLoad(WebElement button, String pageTitleLbl, String expectedURL){
        boolean pageTitle = true;
        browser.navigateToBaseUrl();
        //browser.setCurrentPage(new TasksListHomePage(browser)); // to do later remove the browser passed to page object
        //TasksListHomePage page = (TasksListHomePage)browser.getCurrentPage();

        button.click();

        WebDriverWait waitForResponse = new WebDriverWait(browser.getDriver(), 5);
        waitForResponse.until(ExpectedConditions.visibilityOfElementLocated(By.className("panel-title")));

        // Check if page title is present
        String titleLbl = page.getTitleLabel();
        if (titleLbl.equals(pageTitleLbl)){
            System.out.println("Title : " + titleLbl + " is present");
        }else {
            pageTitle = false;
        }
        return (expectedURL.equals(browser.getCurrentUrl()) && pageTitle);
    }

    @AfterClass(groups = {"web"})
    public void tearDown() {
        browser.quit();
    }

}
