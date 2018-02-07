package org.enoch.snark.instance;

import org.enoch.snark.model.SourcePlanet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.enoch.snark.instance.PropertyNames.*;
import static org.enoch.snark.instance.PropertyNames.PLANET_IDS;

public class AppProperties {

    public String username;
    public String password;
    public String server;

    public String loginUrl;
    public String mainUrl;

    public Integer fleetNumber;
    public List<SourcePlanet> sourcePlanets = new ArrayList<>();

    public static String pathToChromeWebdriver;

    AppProperties(String pathToProperties) throws IOException {
        Properties properties = new java.util.Properties();
        FileInputStream fileInputStream = new FileInputStream(pathToProperties);
        properties.load(fileInputStream);

        username = properties.getProperty(USER_NAME_LOGIN);
        password = properties.getProperty(PASSWORD_LOGIN);
        server = properties.getProperty(SERVER_LOGIN);

        loginUrl = properties.getProperty(LOGIN_URL);
        mainUrl = properties.getProperty(PLANET_PATTERN_URL);

        fleetNumber = Integer.parseInt(properties.getProperty(FLEET_NUMBER));

        String[] planets = properties.getProperty(PLANET_IDS).split(";");
        for(String planetCode : planets) {
            sourcePlanets.add(new SourcePlanet(planetCode));
        }

        pathToChromeWebdriver = properties.getProperty(WEBDRIVER_CHROME_DRIVER);

        fileInputStream.close();
    }
}
