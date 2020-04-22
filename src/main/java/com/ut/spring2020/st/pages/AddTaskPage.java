package com.ut.spring2020.st.pages;

import com.ut.spring2020.st.framework.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-22, Wed
 * webautomation
 **/

public class AddTaskPage extends Page{

    @FindBy(className = "panel-title")
    private WebElement titleLabel;

    @FindBy(id = "dashboard")
    private WebElement dashboardBtn;

    @FindBy(name = "name")
    private WebElement taskNameTxtBox;

    @FindBy(name = "status")
    private WebElement taskStatusTxtBox;

    @FindBy(id = "add_task")
    private WebElement addTaskBtn;

    @FindBy(className = "btn")
    private List<WebElement> addTaskPageBtns;


    public AddTaskPage(Browser browser) {
        super(browser);
    }

    public WebElement getTitleLabel() {
        this.waitForElement(titleLabel);
        return titleLabel;
    }

    public WebElement getDashboardBtn() {
        this.waitForElement(dashboardBtn);
        return dashboardBtn;
    }

    public WebElement getAddTaskBtn() {
        this.waitForElement(addTaskBtn);
        return addTaskBtn;
    }

    public WebElement gettaskNameTxtBox() {
        this.waitForElement(taskNameTxtBox);
        return taskNameTxtBox;
    }

    public WebElement gettaskStatusTxtBox() {
        this.waitForElement(taskStatusTxtBox);
        return taskStatusTxtBox;
    }

    public List<WebElement> getAddTaskPageBtns() {
        this.waitForElement(addTaskPageBtns);
        return addTaskPageBtns;
    }
}
