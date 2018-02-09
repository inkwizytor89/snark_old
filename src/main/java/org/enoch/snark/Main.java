package org.enoch.snark;

import org.enoch.snark.instance.Universe;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main {

    public static final String DATA_DIR_NAME = "data/";

    public static void main(String[] args) throws IOException {
        final File data = new File(DATA_DIR_NAME);
        for(File accountDir : Objects.requireNonNull(data.listFiles())) {
            if(accountDir.isDirectory()){
                Runnable task = new Universe(accountDir.getAbsolutePath())::runSI;
                new Thread(task).start();
            }
        }

    }
}
