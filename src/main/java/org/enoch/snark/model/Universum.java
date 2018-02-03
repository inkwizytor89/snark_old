package org.enoch.snark.model;

import org.enoch.snark.AppProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.enoch.snark.model.Property.*;

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
}
