package org.enoch.snark.gi.macro;

import org.enoch.snark.gi.macro.Mission;
import org.enoch.snark.instance.Universe;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SourcePlanet;

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
        universe.session.getSelenium().open(builder);
    }

    public void openMessages() {
        String builder = universe.appProperties.mainUrl + "?" +
                "page=" + PAGE_MESSAGES;
        universe.session.getSelenium().open(builder);
    }
}
