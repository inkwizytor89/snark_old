package org.enoch.snark.gi.macro;

import com.thoughtworks.selenium.Selenium;
import org.enoch.snark.gi.GISession;
import org.openqa.selenium.chrome.ChromeDriver;

public class FleetSelector {

    protected final GISession session;
    protected final ChromeDriver chromeDriver;
    protected final Selenium selenium;

    public FleetSelector(GISession session){
        this.session = session;
        chromeDriver = this.session.getChromeDriver();
        selenium = this.session.getSelenium();
    }

    public FleetSelector type(Fleet fleet, Integer count) {
        selenium.type(fleet.getId(), count.toString());
        return this;
    }
}
