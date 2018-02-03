package org.enoch.snark;

import org.enoch.snark.model.SourcePlanet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.enoch.snark.model.Property.*;
import static org.enoch.snark.model.Property.PLANET_IDS;

public class AppProperties {

    private static final String CONFIG_FILE_NAME = "application.properties";
    private static final String CONFIG_DIR_NAME = "src\\main\\resources\\";

    public static String username;
    public static String password;
    public static String server;

    public static Integer fleetNumber;

    public static List<SourcePlanet> sourcePlanets;

    public static void loadApplicationProperties() throws IOException {
        Properties properties = new java.util.Properties();
        FileInputStream fileInputStream = new FileInputStream(CONFIG_DIR_NAME+CONFIG_FILE_NAME);
        properties.load(fileInputStream);

        username = properties.getProperty(USER_NAME_LOGIN);
        password = properties.getProperty(PASSWORD_LOGIN);
        server = properties.getProperty(SERVER_LOGIN);
        fleetNumber = Integer.parseInt(properties.getProperty(FLEET_NUMBER));

        String[] planets = properties.getProperty(PLANET_IDS).split(";");
        for(String planetCode : planets) {
            sourcePlanets.add(new SourcePlanet(planetCode));
        }
        fileInputStream.close();
    }
}
