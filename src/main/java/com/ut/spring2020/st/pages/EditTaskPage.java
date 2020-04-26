package com.ut.spring2020.st.pages;

import com.ut.spring2020.st.framework.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author : Name
 * @since : 2020-04-25, Sat
 * webautomation
 **/

public class EditTaskPage extends Page{

    public EditTaskPage(Browser browser) {
        super(browser);
    }

    @FindBy(className = "panel-title")
    private WebElement titleLabel;

    //get this id added to teh edit task page - TODO Andy
    @FindBy(id = "dashboard")
    private WebElement dashboardBtn;

    @FindBy(name = "name")
    private WebElement taskNameTxtBox;

    @FindBy(name = "status")
    private WebElement taskStatusTxtBox;

    //get id added to the edit task page - TODO Andy
    @FindBy(className = "btn-success")
    private WebElement updateTaskBtn;

    @FindBy(className = "btn")
    private List<WebElement> editTaskPageBtns;

    public WebElement getTitleLabel() {
        this.waitForElement(titleLabel);
        return titleLabel;
    }

    public WebElement getDashboardBtn() {
        this.waitForElement(dashboardBtn);
        return dashboardBtn;
    }

    public WebElement getTaskNameTxtBox() {
        this.waitForElement(taskNameTxtBox);
        return taskNameTxtBox;
    }

    public WebElement getTaskStatusTxtBox() {
        this.waitForElement(taskStatusTxtBox);
        return taskStatusTxtBox;
    }

    public WebElement getUpdateTaskBtn() {
        this.waitForElement(updateTaskBtn);
        return updateTaskBtn;
    }

    public List<WebElement> getEditTaskPageBtns() {
        this.waitForElement(editTaskPageBtns);
        return editTaskPageBtns;
    }
}
