package org.enoch.snark.gi;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.enoch.snark.instance.AppProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.enoch.snark.instance.PropertyNames.WEBDRIVER_CHROME_DRIVER;

public class GISession {

    private final ChromeDriver chromeDriver;
    private final Selenium selenium;

    private boolean isLoggedIn = false;
    private AppProperties appProperties;


    public GISession(AppProperties appProperties) {
        this.appProperties = appProperties;
        System.setProperty(WEBDRIVER_CHROME_DRIVER, AppProperties.pathToChromeWebdriver);
        chromeDriver = new ChromeDriver();
        selenium = new WebDriverBackedSelenium(chromeDriver, appProperties.loginUrl);
    }

    public void open() {
        selenium.open(appProperties.loginUrl);
        logIn();
    }

    public void close() {
        logOut();
        selenium.close();
        chromeDriver.quit();
    }

    private void logIn() {
        final WebElement loginBtn = chromeDriver.findElement(By.id("loginBtn"));
        if(loginBtn.isDisplayed()) {
            chromeDriver.findElementByLinkText("x").click();
        }
        loginBtn.click();
        chromeDriver.findElement(By.id("usernameLogin")).click();
        selenium.type("id=usernameLogin", appProperties.username);
        selenium.type("id=passwordLogin", appProperties.password);
        selenium.type("id=serverLogin", appProperties.server);
        chromeDriver.findElement(By.id("loginSubmit")).click();

        isLoggedIn = true;
    }

    private void logOut() {
        chromeDriver.findElementByLinkText("Wyloguj").click();
        isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public ChromeDriver getChromeDriver() {
        return chromeDriver;
    }

    public Selenium getSelenium() {
        return selenium;
    }
}
