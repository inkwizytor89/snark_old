package org.enoch.snark.gi;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.enoch.snark.AppProperties;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static org.enoch.snark.AppProperties.WEBDRIVER_CHROME_DRIVER;

public class AbstractSeleniumTest {

    protected final ChromeDriver chromeDriver;
    protected final Selenium selenium;

    AbstractSeleniumTest() {
        try {
            AppProperties.loadApplicationProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty(WEBDRIVER_CHROME_DRIVER, AppProperties.pathToChromeWebdriver);
        chromeDriver = new ChromeDriver();
        selenium = new WebDriverBackedSelenium(chromeDriver, GISession.loginUrl);
    }
}
