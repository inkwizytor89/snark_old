package org.enoch.snark.instance;

import org.enoch.snark.gi.GISession;
import org.enoch.snark.gi.Commander;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SourcePlanet;

import java.io.IOException;

public class Universum {

    public final AppProperties appProperties;
    public final Commander commander;
    public final GISession session;

    public Universum(String pathToPropertiesFile) throws IOException {
        appProperties = new AppProperties(pathToPropertiesFile);

        session = new GISession(appProperties);
        commander = new Commander(session);
    }

    public void runSI() {
        new SI(this).run();
    }

    public SourcePlanet findNearestSource(Planet planet) {
        SourcePlanet nearestPlanet = appProperties.sourcePlanets.get(0);
        Integer minDistance = planet.calculateDistance(nearestPlanet);

        for(SourcePlanet source : appProperties.sourcePlanets) {
            Integer distance = planet.calculateDistance(source);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPlanet = source;
            }
        }
        return nearestPlanet;
    }
}
