package org.enoch.snark;

import org.enoch.snark.gi.Commander;
import org.enoch.snark.model.Universum;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        AppProperties.loadApplicationProperties();
        Universum.createInstance();
        Commander.createInstance();
        new SI().run();
    }
}
