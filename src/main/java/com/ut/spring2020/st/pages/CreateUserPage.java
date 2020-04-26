package com.ut.spring2020.st.pages;

import com.ut.spring2020.st.framework.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-26, Sun
 * webautomation
 **/

public class CreateUserPage extends Page  {

    @FindBy(className = "panel-title")
    private WebElement titleLabel;

    @FindBy(name = "name")
    private WebElement nameTxtBox;

    @FindBy(name = "address")
    private WebElement addrTxtBox;

    @FindBy(name = "city")
    private WebElement cityTxtBox;

    @FindBy(name = "state")
    private WebElement stateTxtBox;

    @FindBy(name = "phone")
    private WebElement phoneTxtBox;

    @FindBy(name = "email")
    private WebElement emialTxtBox;

    @FindBy(id = "create_user")
    private WebElement createUsrBtn;

    @FindBy(className = "btn")
    private List<WebElement> createUsrPageBtns;

    public CreateUserPage(Browser browser) {
        super(browser);
    }

    public WebElement getNameTxtBox() {
        this.waitForElement(nameTxtBox);
        return nameTxtBox;
    }

    public WebElement getAddrTxtBox() {
        this.waitForElement(addrTxtBox);
        return addrTxtBox;
    }

    public WebElement getCityTxtBox() {
        this.waitForElement(cityTxtBox);
        return cityTxtBox;
    }

    public WebElement getStateTxtBox() {
        this.waitForElement(stateTxtBox);
        return stateTxtBox;
    }

    public WebElement getPhoneTxtBox() {
        this.waitForElement(phoneTxtBox);
        return phoneTxtBox;
    }

    public WebElement getEmialTxtBox() {
        this.waitForElement(emialTxtBox);
        return emialTxtBox;
    }

    public List<WebElement> getCreateUsrPageBtns() {
        this.waitForElement(createUsrPageBtns);
        return createUsrPageBtns;
    }

    public WebElement getTitleLabel() {
        this.waitForElement(titleLabel);
        return titleLabel;
    }

    public WebElement getCreateUsrBtn() {
        this.waitForElement(createUsrBtn);
        return createUsrBtn;
    }
}
