package com.ut.spring2020.st.tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.ut.spring2020.st.data.User;
import com.ut.spring2020.st.framework.Browser;
import com.ut.spring2020.st.pages.*;
import com.ut.spring2020.st.utilities.TestReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author : Name
 * @since : 2020-04-26, Sun
 * webautomation
 **/

public class ManagerUserTests extends BaseTest {
    Browser browser;
    User testUser;
    String baseURL;
    @Parameters({"browserName", "baseUrl"})
    @BeforeClass(groups = {"web"})
    public void setUp(String browserName, String baseUrl) {
        browser = new Browser(browserName, baseUrl);
        testUser = null;
        baseURL = baseUrl;
    }

    /* - Test cases -
     * 1. Create a user
     * */
    @Test(groups = {"web"})
    public void createUser() throws InterruptedException, IOException {
        String titleLbl;

        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksDashboardPage(browser));
        TasksDashboardPage tasksDashboardPage = (TasksDashboardPage)browser.getCurrentPage();

        // 1. Load dashboard
        boolean pageTitle = true;
        titleLbl = tasksDashboardPage.getTitleLabel();
        if (titleLbl.equals("Task Dashboard")){
            TestReport.createTestNode("Task Dashboard loaded");
            Assert.assertTrue(true);
            TestReport.logTest(Status.PASS,"Task dashboard loaded");
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Task dashboard page", mediaModel);
        }else {
            pageTitle = false;
        }

        //2. Navigate to create user screen
        tasksDashboardPage.getCreateUsrBtn().click();
        TestReport.logTest(Status.PASS,"Create user button clicked");

        browser.setCurrentPage(new CreateUserPage(browser));
        CreateUserPage userPage = (CreateUserPage)browser.getCurrentPage();
        if(userPage.getTitleLabel().getText().equals("Create User")) {
            this.clearFields(userPage);
            TestReport.createTestNode("Create User page loaded");
            TestReport.logTest(Status.INFO,"Create User page loaded");
            testUser = User.createTestUser();
            userPage.getNameTxtBox().sendKeys(testUser.getName());
            userPage.getAddrTxtBox().sendKeys(testUser.getAddress());
            userPage.getCityTxtBox().sendKeys(testUser.getCity());
            userPage.getStateTxtBox().sendKeys(testUser.getState());
            userPage.getPhoneTxtBox().sendKeys(testUser.getPhone());
            userPage.getEmialTxtBox().sendKeys(testUser.getEmail());
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Create user page", mediaModel);
            userPage.getCreateUsrBtn().click();

        }
        boolean createUser = false;
        browser.setCurrentPage(new TasksDashboardPage(browser));
        TasksDashboardPage dashboardPage = (TasksDashboardPage)browser.getCurrentPage();
        if(dashboardPage.getTitleLabel().equals("Task Dashboard")) { //ideally create user should be redirecting to view users page. For now it navigates to dashboard page
            dashboardPage.getViewUsrBtn().click();
            if(browser.getDriver().getCurrentUrl().equals("http://reactboot.cfapps.io/user")){
                TestReport.logTest(Status.PASS,testUser.toString());
                createUser=true;
                TestReport.createTestNode("View User page loaded");
                TestReport.logTest(Status.INFO,"View User page loaded");
                MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
                TestReport.getExtentTest().info("View Users page", mediaModel);
            }
        }

        Assert.assertTrue(pageTitle && createUser);
    }

    @Test(groups = {"web"})
    public void editUser() throws InterruptedException, IOException {
        String titleLbl;

        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksDashboardPage(browser));
        TasksDashboardPage tasksDashboardPage = (TasksDashboardPage)browser.getCurrentPage();

        // 1. Load dashboard
        boolean pageTitle = true;
        titleLbl = tasksDashboardPage.getTitleLabel();
        if (titleLbl.equals("Task Dashboard")){
            TestReport.createTestNode("Task Dashboard loaded");
            Assert.assertTrue(true);
            TestReport.logTest(Status.PASS,"Task dashboard loaded");
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Task dashboard page", mediaModel);
        }else {
            pageTitle = false;
        }

        //2. Navigate to View user screen
        tasksDashboardPage.getViewUsrBtn().click();
        TestReport.logTest(Status.PASS,"View user button clicked");

        browser.setCurrentPage(new ViewUsersPage(browser));
        ViewUsersPage userPage = (ViewUsersPage)browser.getCurrentPage();
        if(userPage.getTitleLabel().getText().equals("Users")) {
            //3. Find the test user and click to go to user details screen
            WebElement userNameElem = browser.getDriver().findElement(By.linkText(testUser.getName()));
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("View user page", mediaModel);
            if(userNameElem !=null) {
                TestReport.logTest(Status.PASS, "Found test user : " + userNameElem.getText());
                String strText = userNameElem.getText();
                userNameElem.click();
                TestReport.logTest(Status.PASS, "Clicked on the test user : " + strText);
            }
        }

        boolean editUser = false;
        browser.setCurrentPage(new UserDetailsPage(browser));
        UserDetailsPage userDetailsPage = (UserDetailsPage)browser.getCurrentPage();
        String userDetailURL = browser.getCurrentUrl();
        //4. set new values and click edit user screen
        if(userDetailsPage.getTitleLabel().getText().equals("User Details")) {
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("User details page loaded", mediaModel);
            userDetailsPage.getEditUserBtn().click();
            browser.setCurrentPage(new EditUserPage(browser));
            EditUserPage editUserPage = (EditUserPage)browser.getCurrentPage();
            this.clearFields(editUserPage);

            testUser = User.updateTestUser(testUser);
            editUserPage.getNameTxtBox().sendKeys(testUser.getName());
            editUserPage.getAddrTxtBox().sendKeys(testUser.getAddress());
            editUserPage.getCityTxtBox().sendKeys(testUser.getCity());
            editUserPage.getStateTxtBox().sendKeys(testUser.getState());
            editUserPage.getPhoneTxtBox().sendKeys(testUser.getPhone());
            editUserPage.getEmialTxtBox().sendKeys(testUser.getEmail());

            MediaEntityModelProvider mediaModel2 = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Edit user details", mediaModel2);
            editUserPage.getUpdateUserBtn().click();
            TestReport.logTest(Status.INFO,"Update user clicked");
            //5. Verify if the user is successfully edited
            if(browser.getDriver().getCurrentUrl().equals(userDetailURL)){
                browser.setCurrentPage(new UserDetailsPage(browser));
                UserDetailsPage detailsPage = (UserDetailsPage)browser.getCurrentPage();
                if(detailsPage.getTitleLabel().getText().equals("User Details")) {
                    editUser=true;
                    TestReport.createTestNode("User edited");
                    TestReport.logTest(Status.INFO, "User edited");
                    MediaEntityModelProvider mediaModel3 = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
                    TestReport.getExtentTest().info("User Edited", mediaModel3);
                }
            }
        }

        Assert.assertTrue(pageTitle && editUser);
    }

    @Test(groups = {"web"})
    public void deleteUser() throws InterruptedException, IOException {
        String titleLbl;

        browser.navigateToBaseUrl();
        browser.setCurrentPage(new TasksDashboardPage(browser));
        TasksDashboardPage tasksDashboardPage = (TasksDashboardPage)browser.getCurrentPage();

        // 1. Load dashboard
        boolean pageTitle = true;
        titleLbl = tasksDashboardPage.getTitleLabel();
        if (titleLbl.equals("Task Dashboard")){
            TestReport.createTestNode("View Dashboard");
            Assert.assertTrue(true);
            TestReport.logTest(Status.PASS,"Task dashboard loaded");
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Task dashboard page", mediaModel);
        }else {
            pageTitle = false;
        }

        //2. Navigate to View user screen
        tasksDashboardPage.getViewUsrBtn().click();
        TestReport.logTest(Status.PASS,"View user button clicked");

        browser.setCurrentPage(new ViewUsersPage(browser));
        ViewUsersPage userPage = (ViewUsersPage)browser.getCurrentPage();
        if(userPage.getTitleLabel().getText().equals("Users")) {
            //3. Find the test user and click to go to user details screen
            WebElement userNameElem = browser.getDriver().findElement(By.linkText(testUser.getName()));
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Users list page before delete", mediaModel);
            if(userNameElem !=null) {
                TestReport.logTest(Status.PASS, "Found test user : " + userNameElem.getText());
                String strText = userNameElem.getText();
                userNameElem.click();
                TestReport.logTest(Status.PASS, "Clicked on the test user : " + strText);
            }
        }

        boolean deleteUser = false;
        browser.setCurrentPage(new UserDetailsPage(browser));
        UserDetailsPage userDetailsPage = (UserDetailsPage)browser.getCurrentPage();
        String userDetailURL = browser.getCurrentUrl();
        //4. set new values and click edit user screen
        if(userDetailsPage.getTitleLabel().getText().equals("User Details")) {
            TestReport.createTestNode("View User");
            MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
            TestReport.getExtentTest().info("Users details page", mediaModel);
            userDetailsPage.getDeleteUserBtn().click();
            TestReport.logTest(Status.INFO,"Delete user clicked");
            //5. Verify if the user is successfully deleted
            if(browser.getDriver().getCurrentUrl().equals(baseURL)){
                browser.setCurrentPage(new TasksDashboardPage(browser));
                TasksDashboardPage dashboardPage = (TasksDashboardPage)browser.getCurrentPage();
                if(dashboardPage.getTitleLabel().equals("Task Dashboard")) {
                    deleteUser=true;
                    dashboardPage.getViewUsrBtn().click();

                    browser.setCurrentPage(new ViewUsersPage(browser));
                    ViewUsersPage usersPage = (ViewUsersPage)browser.getCurrentPage();
                    if(usersPage.getTitleLabel().getText().equals("Users")) {
                        TestReport.createTestNode("Delete User");
                        TestReport.logTest(Status.INFO, "User deleted");
                        MediaEntityModelProvider mediaModel3 = MediaEntityBuilder.createScreenCaptureFromPath(TestReport.capture(browser.getDriver())).build();
                        TestReport.getExtentTest().info("Users list page after delete", mediaModel3);
                    }
                }
            }
        }

        Assert.assertTrue(pageTitle && deleteUser);
    }

    private void clearFields(CreateUserPage userPage){
        userPage.getNameTxtBox().clear();
        userPage.getAddrTxtBox().clear();
        userPage.getCityTxtBox().clear();
        userPage.getStateTxtBox().clear();
        userPage.getPhoneTxtBox().clear();
        userPage.getEmialTxtBox().clear();
    }

    @AfterClass(groups = {"web"})
    public void tearDown() {
        browser.quit();
    }

}
