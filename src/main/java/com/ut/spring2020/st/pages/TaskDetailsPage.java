package com.ut.spring2020.st.pages;

import com.ut.spring2020.st.framework.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-25, Sat
 * webautomation
 **/

public class TaskDetailsPage extends Page {

    public TaskDetailsPage(Browser browser) {
        super(browser);
    }

    @FindBy(className = "panel-title")
    private WebElement titleLabel;

    @FindBy(className = "btn")
    private List<WebElement> taskDetailsBtns;

    @FindBy(id = "view_tasks")
    private WebElement viewTasksBtn;

    @FindBy(id = "complete")
    private WebElement markCompleteBtn;

    @FindBy(id = "edit_task")
    private WebElement editTaskBtn;

    @FindBy(id = "delete_task")
    private WebElement deleteTaskBtn;

    public WebElement getTitleLabel() {
        this.waitForElement(titleLabel);
        return titleLabel;
    }

    public List<WebElement> getTaskDetailsBtns() {
        this.waitForElement(taskDetailsBtns);
        return taskDetailsBtns;
    }

    public WebElement getViewTasksBtn() {
        this.waitForElement(viewTasksBtn);
        return viewTasksBtn;
    }

    public WebElement getMarkCompleteBtn() {
        this.waitForElement(markCompleteBtn);
        return markCompleteBtn;
    }

    public WebElement getEditTaskBtn() {
        this.waitForElement(editTaskBtn);
        return editTaskBtn;
    }

    public WebElement getDeleteTaskBtn() {
        this.waitForElement(deleteTaskBtn);
        return deleteTaskBtn;
    }
}
