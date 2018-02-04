package org.enoch.snark;

import org.enoch.snark.gi.Commander;
import org.enoch.snark.model.Universum;

import java.io.IOException;

public class Main {

    private static final String CONFIG_FILE_NAME = "application.properties";
    private static final String CONFIG_DIR_NAME = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        AppProperties.loadApplicationProperties(CONFIG_DIR_NAME+CONFIG_FILE_NAME);
        Universum.createInstance();
        Commander.createInstance();
        new SI().run();
    }
}
