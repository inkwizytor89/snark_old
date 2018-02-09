package org.enoch.snark;

import org.enoch.snark.instance.Universe;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main {

    public static final String CONFIG_FILE_NAME = "application.properties";
    public static final String CONFIG_DIR_NAME = "data/";

    public static void main(String[] args) throws IOException {
        final File data = new File(CONFIG_DIR_NAME);
        for(File dir : Objects.requireNonNull(data.listFiles())) {
            if(dir.isDirectory()){
                Runnable task = new Universe(dir.getAbsolutePath() + CONFIG_FILE_NAME)::runSI;
                new Thread(task).start();
            }
        }

    }
}
