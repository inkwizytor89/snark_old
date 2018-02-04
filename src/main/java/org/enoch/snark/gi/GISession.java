package org.enoch.snark.gi;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.enoch.snark.AppProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.enoch.snark.PropertyNames.WEBDRIVER_CHROME_DRIVER;

public class GISession {

    private final ChromeDriver chromeDriver;
    private final Selenium selenium;

    private boolean isLoggedIn = false;


    GISession() {
        System.setProperty(WEBDRIVER_CHROME_DRIVER, AppProperties.pathToChromeWebdriver);
        chromeDriver = new ChromeDriver();
        selenium = new WebDriverBackedSelenium(chromeDriver, AppProperties.loginUrl);
    }

    public void open() {
        selenium.open(AppProperties.loginUrl);
        logIn();
    }

    public void close() {
        logOut();
        selenium.close();
        chromeDriver.quit();
    }

    private void logIn() {
        chromeDriver.findElement(By.id("loginBtn")).click();
        chromeDriver.findElement(By.id("usernameLogin")).click();
        selenium.type("id=usernameLogin", AppProperties.username);
        selenium.type("id=passwordLogin", AppProperties.password);
        selenium.type("id=serverLogin", AppProperties.server);
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
