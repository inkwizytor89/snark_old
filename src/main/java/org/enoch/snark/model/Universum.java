package org.enoch.snark.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.enoch.snark.model.Property.*;

public class Universum {

    private static final String CONFIG_FILE_NAME = "application.properties";
    private static final String CONFIG_DIR_NAME = "src\\main\\resources\\";
    private static Universum instance  = null;

    private static List<SourcePlanet> sourcePlanets;
    public String username;
    public String password;
    public String server;

    public Integer fleetNumber;

    private Universum() throws IOException {
        sourcePlanets = new ArrayList<>();
        loadDataFromConfigFile();
    }

    private void loadDataFromConfigFile() throws IOException {
        Properties properties = new Properties();
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

    public static Universum getInstance() throws IOException {
        if(instance == null) {
            instance = new Universum();
        }
        return instance;
    }
}
