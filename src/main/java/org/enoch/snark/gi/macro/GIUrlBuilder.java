package org.enoch.snark.gi.macro;

import org.enoch.snark.instance.Universe;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SourcePlanet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GIUrlBuilder {

    private static final String PAGE_OVERVIEW = "overview";
    private static final String PAGE_BASE_FLEET = "fleet1";
    private static final String PAGE_MESSAGES = "messages";

    private Universe universe;

    public GIUrlBuilder(Universe universe) {
        this.universe = universe;
    }

    public void openFleetView(SourcePlanet planet, Planet target, Mission mission) {
        String builder = universe.appProperties.mainUrl + "?" +
                "page=" + PAGE_BASE_FLEET +
                "&cp=" + planet.planetId +
                "&galaxy=" + target.galaxy +
                "&system=" + target.system +
                "&position=" + target.position +
                "&type=1&mission=" + mission.getValue();
        universe.session.getSeleniumDriver().open(builder);

        loadFleetStatus();
    }

    public void updateFleetStatus() {
        String builder = universe.appProperties.mainUrl + "?" +
                "page=" + PAGE_BASE_FLEET ;
        universe.session.getSeleniumDriver().open(builder);

        loadFleetStatus();
    }

    private void loadFleetStatus() {
        final WebElement slotsLabel = universe.session.getChromeDriver().findElement(By.id("slots"));
        final String[] split = slotsLabel.getText().split("\\s");
        universe.commander.setFleetStatus(returnValue(split[2]), returnMax(split[2]));
        universe.commander.setExpeditionStatus(returnValue(split[4]), returnMax(split[4]));
    }

    private int returnValue(String status) {
        return Integer.parseInt(status.split("/")[0]);
    }

    private int returnMax(String status) {
        return Integer.parseInt(status.split("/")[1]);
    }

    public void openMessages() {
        String builder = universe.appProperties.mainUrl + "?" +
                "page=" + PAGE_MESSAGES;
        universe.session.getSeleniumDriver().open(builder);
    }

    public void openOverview() {
        String builder = universe.appProperties.mainUrl + "?" +
                "page=" + PAGE_OVERVIEW;
        universe.session.getSeleniumDriver().open(builder);
    }
}
