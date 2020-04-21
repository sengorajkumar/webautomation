package com.ut.spring2020.st.framework;

/**
 * @author : Rajkumar Sengottuvel
 * @since : 2020-04-20, Mon
 * webautomation
 **/

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.ut.spring2020.st.pages.Page;
import com.ut.spring2020.st.pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Browser {


    public Browser(String browserName, String baseUrl) {
        setBrowser(browserName);
        setBaseUrl(baseUrl);
        Initialise(getBrowser());
    }

    private void Initialise(String browser) {
        capabilities = new DesiredCapabilities();
        seleniumFolderPath = System.getProperty("user.dir") + "/";
        System.out.println(seleniumFolderPath);

        switch (browser) {
             case "Firefox":
                FirefoxProfile ff_profile = new FirefoxProfile();
                ff_profile.setPreference("geo.prompt.testing", true);
                ff_profile.setPreference("geo.prompt.testing.allow", true);
                System.setProperty("webdriver.gecko.driver", seleniumFolderPath + "geckodriver");
                capabilities.setBrowserName("firefox");
                capabilities.setCapability(FirefoxDriver.PROFILE, ff_profile);

                driver = new FirefoxDriver(capabilities);
                break;
            default:
                System.out.println("Invalid browser passed in: " + browser);
                break;
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public String getCurrentUrl(){
        return this.driver.getCurrentUrl();
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void navigateToBaseUrl() {
        driver.get(getBaseUrl());
    }

    public String getBrowser() {
        return this.browserName;
    }

    private void setBrowser(String browserName) {
        this.browserName = browserName;
    }

    private void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public Page HomePage() {
        if (currentPage == null) {
            currentPage = new HomePage(this);
        }
        return this.currentPage;
    }


    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public void quit(){
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver driver;
    private DesiredCapabilities capabilities;
    private String browserName;
    private String baseUrl;
    private String seleniumFolderPath;
    private Page currentPage;
}
