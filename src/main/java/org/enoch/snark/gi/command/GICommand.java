package org.enoch.snark.gi.command;

import com.thoughtworks.selenium.Selenium;
import org.enoch.snark.gi.GISession;
import org.enoch.snark.instance.Universe;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class GICommand extends AbstractCommand {

    protected final GISession session;
    protected final ChromeDriver chromeDriver;
    protected final Selenium selenium;

    protected GICommand(Universe universe, CommandType type) {
        super(universe, type);
        session = universe.session;
        chromeDriver = session.getChromeDriver();
        selenium = session.getSeleniumDriver();
    }
}
