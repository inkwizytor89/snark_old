package org.enoch.snark.gi;

import org.enoch.snark.instance.Universum;
import org.enoch.snark.model.Planet;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

public class AbstractSeleniumTest {

    static final String CONFIG_FILE_NAME = "application.properties";
    static final String CONFIG_DIR_NAME = "src/test/resources/";

    protected Universum universum;
    protected Planet sampleTarget;

    protected AbstractSeleniumTest() {
        try {
            universum = new Universum(CONFIG_DIR_NAME + CONFIG_FILE_NAME, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sampleTarget = new Planet("[3:104:7]");
    }

    @Before
    public void setUp() {
        universum.session.open();
    }

    @After
    public void tearDown() {
        universum.session.close();
    }
}
