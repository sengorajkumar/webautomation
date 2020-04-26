package com.ut.spring2020.st.pages;

import com.ut.spring2020.st.framework.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-26, Sun
 * webautomation
 **/

public class ViewUsersPage extends Page {

    @FindBy(className = "panel-title")
    private WebElement titleLabel;

    @FindBy(id = "create_user")
    private WebElement createUserBtn;

    public ViewUsersPage(Browser browser) {
        super(browser);
    }

    public WebElement getTitleLabel() {
        this.waitForElement(titleLabel);
        return titleLabel;
    }

    public WebElement getCreateUserBtn() {
        this.waitForElement(createUserBtn);
        return createUserBtn;
    }
}
