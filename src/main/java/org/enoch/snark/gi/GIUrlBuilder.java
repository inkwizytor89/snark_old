package org.enoch.snark.gi;

import org.enoch.snark.gi.macro.Mission;
import org.enoch.snark.instance.Universum;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SourcePlanet;

public class GIUrlBuilder {

    private static final String PAGE_OVERVIEW = "overview";
    private static final String PAGE_BASE_FLEET = "fleet1";

    private Universum universum;

    public GIUrlBuilder(Universum universum) {
        this.universum = universum;
    }

    public void openFleetView(SourcePlanet planet, Planet target, Mission mission) {
        String builder = universum.appProperties.mainUrl + "?" +
                "page=" + PAGE_BASE_FLEET +
                "&cp=" + planet.planetId +
                "&galaxy=" + target.galaxy +
                "&system=" + target.system +
                "&position=" + target.position +
                "&type=1&mission=" + mission.getValue();
        universum.session.getSelenium().open(builder);
    }
}
