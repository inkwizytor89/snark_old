package org.enoch.snark;

import org.enoch.snark.model.Universum;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Universum.getInstance();
        Commander.getInstance();
        new SI().run();
    }
}
