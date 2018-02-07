package org.enoch.snark.gi.command;

import com.thoughtworks.selenium.Selenium;
import org.enoch.snark.gi.Commander;
import org.enoch.snark.gi.GISession;
import org.enoch.snark.instance.Universum;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class GICommand extends AbstractCommand {

    protected final GISession session;
    protected final ChromeDriver chromeDriver;
    protected final Selenium selenium;

    protected GICommand(Universum universum, CommandType type) {
        super(universum, type);
        session = universum.session;
        chromeDriver = session.getChromeDriver();
        selenium = session.getSelenium();
    }
}
