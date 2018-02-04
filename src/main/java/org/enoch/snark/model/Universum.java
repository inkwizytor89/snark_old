package org.enoch.snark.model;

import org.enoch.snark.AppProperties;

import java.io.IOException;
import java.util.List;

public class Universum {

    private static List<SourcePlanet> sourcePlanets;
    public Integer fleetNumber;

    private Universum() throws IOException {
        sourcePlanets = AppProperties.sourcePlanets;
        fleetNumber = AppProperties.fleetNumber;
    }

    public static void createInstance() throws IOException {
        new Universum();
    }

    public static SourcePlanet findNearestSource(Planet planet) {
        SourcePlanet nearestPlanet = sourcePlanets.get(0);
        Integer minDistance = planet.calculateDistance(nearestPlanet);

        for(SourcePlanet source : sourcePlanets) {
            Integer distance = planet.calculateDistance(source);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPlanet = source;
            }
        }
        return nearestPlanet;
    }
}
