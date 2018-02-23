package org.enoch.snark.gi.macro;

import com.thoughtworks.selenium.Selenium;
import org.enoch.snark.gi.GISession;
import org.enoch.snark.model.exception.PlanetDoNotExistException;
import org.enoch.snark.model.exception.ShipDoNotExists;
import org.enoch.snark.model.exception.ToStrongPlayerException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class FleetSelector {

    private final Selenium selenium;
    private final GISession session;

    public FleetSelector(GISession session){
        this.session = session;
        selenium = session.getSeleniumDriver();
    }

    public void typeShip(ShipEnum shipEnum, Integer count) {
        selenium.type(shipEnum.getId(), count.toString());
    }

    public void next() {
        session.sleep(TimeUnit.SECONDS, 1);

        final WebElement continueButton = session.getChromeDriver().findElement(By.id("continue"));
        if(continueButton.getAttribute("class").equals("off"))
            throw new ShipDoNotExists();
        continueButton.click();
    }

    public boolean start() {
        session.sleep(TimeUnit.SECONDS, 2);
        final WebElement startInput = session.getChromeDriver().findElementById("start");
        if(startInput.getTagName().equals("td")) {
            throw new PlanetDoNotExistException();
        }
        startInput.click();
        session.sleep(TimeUnit.SECONDS, 1);
        final WebElement errorBoxDecisionNo = session.getChromeDriver().findElement(By.id("errorBoxDecisionNo"));
        if(errorBoxDecisionNo.isDisplayed()) throw new ToStrongPlayerException();
        return true;
    }
}
