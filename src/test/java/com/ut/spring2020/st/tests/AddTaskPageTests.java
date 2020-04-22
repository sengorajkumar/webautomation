package com.ut.spring2020.st.tests;

import com.ut.spring2020.st.framework.Browser;
import com.ut.spring2020.st.pages.AddTaskPage;
import com.ut.spring2020.st.pages.Page;
import com.ut.spring2020.st.pages.TasksListHomePage;
import org.openqa.selenium.By;
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

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-22, Wed
 * webautomation
 **/

public class AddTaskPageTests extends BaseTest{
    Browser browser;
    AddTaskPage page;

    @Parameters({"browserName", "addTaskURL"})
    //@Parameters({"browserName", "baseUrl"})
    @BeforeClass(groups = {"web"})
    public void setUp(String browserName, String addTaskURL) {
        browser = new Browser(browserName, addTaskURL);
        browser.navigateToBaseUrl();
        browser.setCurrentPage(new AddTaskPage(browser)); // to do later remove the browser passed to page object;
        page = (AddTaskPage)browser.getCurrentPage();
    }
    // Test case to check home page loading
    @Test(groups = {"web"})
    public void loadAddTaskPage() throws InterruptedException {
        browser.navigateToBaseUrl();
        browser.setCurrentPage(new AddTaskPage(browser)); // to do later remove the browser passed to page object;
        page = (AddTaskPage)browser.getCurrentPage();

        // Check if page title is present
        boolean pageTitle = true;
        String titleLbl = page.getTitleLabel().getText();
        if (titleLbl.equals("Add Task")){
            System.out.println("Title : " + titleLbl + " is present");
        }else {
            pageTitle = false;
        }

        // Check if all buttons are present
        List<String> expectedButtons =  Arrays.asList( "Add Task", "Return to Dashboard");
        boolean allButtons = true;
        for (WebElement element : page.getAddTaskPageBtns()) {
            if(expectedButtons.contains(element.getText())){
                System.out.println("Button : " + element.getText() + " is present");
            }else {
                System.out.println("Button : " + element.getText() + " is not present");
                allButtons = false;
            }
        }
        Assert.assertTrue(allButtons && pageTitle);
    }


    @AfterClass(groups = {"web"})
    public void tearDown() {
        browser.quit();
    }

}
