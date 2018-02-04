package org.enoch.snark.gi;

import com.thoughtworks.selenium.Selenium;
import org.enoch.snark.AppProperties;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SourcePlanet;
import org.enoch.snark.model.Universum;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class AbstractSeleniumTest {

    public static final String CONFIG_FILE_NAME = "application.properties";
    public static final String CONFIG_DIR_NAME = "src/test/resources/";

    private GISession session;
    protected final ChromeDriver chromeDriver;
    protected final Selenium selenium;

    protected SourcePlanet sampleSource;
    protected Planet sampleTarget;
    protected Planet incorrectSource;

    protected AbstractSeleniumTest() {
        try {
            AppProperties.loadApplicationProperties(CONFIG_DIR_NAME+CONFIG_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        session = new GISession();
        Commander.setSession(session);
        chromeDriver = session.getChromeDriver();
        selenium = session.getSelenium();

        try {
            Universum.createInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sampleSource = AppProperties.sourcePlanets.get(1);
        sampleTarget = new Planet("[3:104:7]");
        incorrectSource = new Planet("[3:104:1]");
    }

    @Before
    public void setUp() {
        session.open();
    }

    @After
    public void tearDown() {
        session.close();
    }
}
