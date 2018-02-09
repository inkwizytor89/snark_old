package org.enoch.snark.instance;

import org.enoch.snark.gi.GISession;
import org.enoch.snark.gi.Commander;
import org.enoch.snark.gi.macro.MessageService;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SourcePlanet;

import java.io.IOException;

public class Universe {

    public final AppProperties appProperties;
    public Commander commander;
    public final GISession session;

    public Universe(String pathToPropertiesFile) throws IOException {
        this(pathToPropertiesFile, true);
    }

    public Universe(String pathToPropertiesFile, boolean isQueueEnabled) throws IOException {
        appProperties = new AppProperties(pathToPropertiesFile);
        new MessageService();
        session = new GISession(appProperties);
        if(isQueueEnabled) {
            commander = new Commander(session);
        }
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
