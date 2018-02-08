package org.enoch.snark.gi;

import org.enoch.snark.instance.Universe;
import org.junit.Before;
import org.junit.Test;

import static org.enoch.snark.gi.AbstractSeleniumTest.CONFIG_DIR_NAME;
import static org.enoch.snark.gi.AbstractSeleniumTest.CONFIG_FILE_NAME;
import static org.junit.Assert.*;

public class GISessionTest {

    private GISession session;
    private Universe universe;

    @Before
    public void setUp() throws Exception {
        universe = new Universe(CONFIG_DIR_NAME + CONFIG_FILE_NAME);
//        AppProperties.loadApplicationProperties(CONFIG_DIR_NAME+CONFIG_FILE_NAME);
    }

    @Test
    public void login_when_openSession() {
        session = universe.session;

        session.open();

        assertEquals(true, session.isLoggedIn());
        session.close();
    }

    @Test
    public void logout_when_closeSession() {
        session = universe.session;
        session.open();

        session.close();
        assertEquals(false, session.isLoggedIn());
    }
}