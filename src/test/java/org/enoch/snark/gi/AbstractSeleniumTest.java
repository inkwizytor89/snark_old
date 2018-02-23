package org.enoch.snark.gi;

import org.enoch.snark.instance.Universe;
import org.enoch.snark.model.Planet;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.enoch.snark.Main.DATA_DIR_NAME;

public class AbstractSeleniumTest {

    protected Universe universe;
    protected Planet sampleTarget;

    protected AbstractSeleniumTest() {
        universe = creatTestInstance();

        sampleTarget = new Planet("[3:104:7]");
    }

    public static Universe creatTestInstance() {
        final File data = new File(DATA_DIR_NAME);
        for(File accountDir : Objects.requireNonNull(data.listFiles())) {
            if(accountDir.isDirectory()){
                try {
                    return new Universe(accountDir.getAbsolutePath(), false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Before
    public void setUp() {
//        universe.session.open();
    }

    @After
    public void tearDown() {
        universe.session.close();
    }
}
