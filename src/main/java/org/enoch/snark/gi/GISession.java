package org.enoch.snark.gi;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.enoch.snark.gi.macro.GIUrlBuilder;
import org.enoch.snark.instance.AppProperties;
import org.enoch.snark.instance.Universe;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.enoch.snark.instance.PropertyNames.WEBDRIVER_CHROME_DRIVER;

public class GISession {

    private final ChromeDriver chromeDriver;
    private final Selenium seleniumDriver;
    private final Universe universe;
    private final SessionHelper sessionHelper;

    private boolean isLoggedIn = false;
    private AppProperties appProperties;


    public GISession(Universe universe) {
        this.universe = universe;
        appProperties = universe.appProperties;
        System.setProperty(WEBDRIVER_CHROME_DRIVER, AppProperties.pathToChromeWebdriver);
        chromeDriver = new ChromeDriver();
        seleniumDriver = new WebDriverBackedSelenium(chromeDriver, appProperties.loginUrl);
        sessionHelper = new SessionHelper(chromeDriver, seleniumDriver);
        open();
    }

    public void open() {
        seleniumDriver.open(appProperties.loginUrl);
        logIn();
    }

    public void close() {
        logOut();
//        seleniumDriver.close();
//        chromeDriver.quit();
    }

    private void logIn() {
        sessionHelper.skipBannerIfExists();
        sessionHelper.insertLoginData(appProperties.username, appProperties.password, appProperties.server);
        isLoggedIn = true;
    }

    private void logOut() {
        new GIUrlBuilder(universe).openOverview();
        chromeDriver.findElementByLinkText("Wyloguj").click();
        isLoggedIn = false;
    }

    public void sleep(TimeUnit timeUnit, int i) {
        try {
            timeUnit.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public ChromeDriver getChromeDriver() {
        return chromeDriver;
    }

    public Selenium getSeleniumDriver() {
        return seleniumDriver;
    }
}
