package org.enoch.snark.gi;

import com.thoughtworks.selenium.Selenium;
import org.enoch.snark.gi.macro.GIUrlBuilder;
import org.enoch.snark.instance.Universe;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

class SessionHelper {
    private Universe universe;
    private final GISession session;
    private final ChromeDriver chromeDriver;
    private final Selenium selenium;

    SessionHelper(Universe universe, GISession session) {
        this.universe = universe;
        this.session = session;
        this.chromeDriver = session.getChromeDriver();
        this.selenium = session.getSeleniumDriver();

        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    public void skipBannerIfExists() {
        try {
            final WebElement exitButton = chromeDriver.findElementByLinkText("x");
            exitButton.click();
        } catch (NoSuchElementException e) {}
    }

    public void insertLoginData(String username, String password, String server) {
        final WebElement loginButton = chromeDriver.findElement(By.id("loginBtn"));
        loginButton.click();

        selenium.type("id=usernameLogin", username);
        selenium.type("id=passwordLogin", password);
        selenium.type("id=serverLogin", server);

        final WebElement loginSubmit = chromeDriver.findElement(By.id("loginSubmit"));
        loginSubmit.click();
    }
}
