package org.enoch.snark.instance;

import org.enoch.snark.gi.GISession;
import org.enoch.snark.gi.Commander;
import org.enoch.snark.gi.macro.MessageService;
import org.enoch.snark.model.Planet;
import org.enoch.snark.model.SourcePlanet;

import java.io.IOException;

public class Universe {
    public static final String CONFIG_FILE_NAME = "/application.properties";

    public final AppProperties appProperties;
    public Commander commander;
    public final GISession session;

    public Universe(String pathToMainDir) throws IOException {
        this(pathToMainDir, true);
    }

    public Universe(String pathToMainDir, boolean isQueueEnabled) throws IOException {
        appProperties = new AppProperties(pathToMainDir+CONFIG_FILE_NAME);
//        new MessageService();
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
