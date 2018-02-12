package org.enoch.snark.gi.macro;

import com.thoughtworks.selenium.Selenium;
import org.enoch.snark.gi.GISession;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class FleetSelector {

    private final Selenium selenium;
    private final GISession session;

    public FleetSelector(GISession session){
        this.session = session;
        selenium = session.getSelenium();
    }

    public void typeShip(Fleet fleet, Integer count) {
        selenium.type(fleet.getId(), count.toString());
    }

    public void next() {
        selenium.click("continue");
    }

    public boolean start() {
        final String tagName = session.getChromeDriver().findElementById("start").getTagName();
        if(tagName.equals("td")) {
            return false; //is not button
        }
        session.sleep(TimeUnit.SECONDS, 2);
        selenium.click("start");
        return true;
    }
}
