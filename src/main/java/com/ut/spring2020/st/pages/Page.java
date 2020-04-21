package com.ut.spring2020.st.pages;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-20, Mon
 * webautomation
 **/

import com.ut.spring2020.st.framework.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Page {
    protected Browser browser;
    private WebDriverWait wait;
    public Page(Browser browser) {
        this.browser=browser;
        PageFactory.initElements(browser.getDriver(), this);
        this.wait = new WebDriverWait(browser.getDriver(), 10);

    }

    public void waitForElement(WebElement elem){
        wait.until(ExpectedConditions.visibilityOf(elem));
    }

    public void waitForElement(List<WebElement> elements){
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

}
