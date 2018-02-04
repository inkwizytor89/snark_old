package org.enoch.snark.gi.command;

import com.thoughtworks.selenium.Selenium;
import org.enoch.snark.AppProperties;
import org.enoch.snark.gi.Commander;
import org.enoch.snark.gi.GISession;
import org.enoch.snark.model.SourcePlanet;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class GICommand extends AbstractCommand {

    protected final GISession session;
    protected final ChromeDriver chromeDriver;
    protected final Selenium selenium;

    protected GICommand(CommandType type) {
        super(type);
        session = Commander.getSession();
        chromeDriver = session.getChromeDriver();
        selenium = session.getSelenium();
    }
}
