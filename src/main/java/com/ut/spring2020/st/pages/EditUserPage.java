package com.ut.spring2020.st.pages;

import com.ut.spring2020.st.framework.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author : Name
 * @since : 2020-04-26, Sun
 * webautomation
 **/

public class EditUserPage extends CreateUserPage {
    public EditUserPage(Browser browser){
        super(browser);
    }

    @FindBy(className = "btn-success")
    private WebElement updateUserBtn;

    public WebElement getUpdateUserBtn() {
        this.waitForElement(updateUserBtn);
        return updateUserBtn;
    }
}
