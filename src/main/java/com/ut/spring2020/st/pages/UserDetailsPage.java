package com.ut.spring2020.st.pages;

import com.ut.spring2020.st.framework.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author : Name
 * @since : 2020-04-26, Sun
 * webautomation
 **/

public class UserDetailsPage extends Page {

    public UserDetailsPage(Browser browser) {
        super(browser);
    }
    @FindBy(className = "panel-title")
    private WebElement titleLabel;

    @FindBy(className = "btn-success")
    private WebElement editUserBtn;

    @FindBy(className = "btn-danger")
    private WebElement deleteUserBtn;

    public WebElement getTitleLabel() {
        this.waitForElement(titleLabel);
        return titleLabel;
    }

    public WebElement getEditUserBtn() {
        this.waitForElement(editUserBtn);
        return editUserBtn;
    }

    public WebElement getDeleteUserBtn() {
        this.waitForElement(deleteUserBtn);
        return deleteUserBtn;
    }
}
