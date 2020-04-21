package com.ut.spring2020.st.pages;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-20, Mon
 * webautomation
 **/
import com.ut.spring2020.st.framework.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TasksListHomePage extends Page {

    @FindBy(className = "panel-title")
    private WebElement titleLabel;

    @FindBy(className = "btn")
    private List<WebElement> hpButtons;

    @FindBy(id = "view_tasks")
    private WebElement viewTasksBtn;

    @FindBy(id = "create_user")
    private WebElement createUsrBtn;

    @FindBy(id = "view_users")
    private WebElement viewUsrBtn;

    @FindBy(id = "add_task")
    private WebElement addTaskBtn;

    public TasksListHomePage(Browser browser) {
        super(browser);
    }

    public String getTitleLabel(){
        this.waitForElement(titleLabel);
        return titleLabel.getText();
    }

    public List<WebElement> getHpButtons() {
        this.waitForElement(hpButtons);
        return hpButtons;
    }

    public WebElement getViewTasksBtn() {
        this.waitForElement(viewTasksBtn);
        return viewTasksBtn;
    }

    public WebElement getCreateUsrBtn() {
        this.waitForElement(createUsrBtn);
        return createUsrBtn;
    }

    public WebElement getViewUsrBtn() {
        this.waitForElement(viewUsrBtn);
        return viewUsrBtn;
    }

    public WebElement getAddTaskBtn() {
        this.waitForElement(addTaskBtn);
        return addTaskBtn;
    }

}
