package org.enoch.snark.gi;

import org.enoch.snark.instance.AppProperties;
import org.enoch.snark.instance.Universum;
import org.junit.Before;
import org.junit.Test;

import static org.enoch.snark.gi.AbstractSeleniumTest.CONFIG_DIR_NAME;
import static org.enoch.snark.gi.AbstractSeleniumTest.CONFIG_FILE_NAME;
import static org.junit.Assert.*;

public class GISessionTest {

    private GISession session;
    private Universum universum;

    @Before
    public void setUp() throws Exception {
        universum = new Universum(CONFIG_DIR_NAME + CONFIG_FILE_NAME);
//        AppProperties.loadApplicationProperties(CONFIG_DIR_NAME+CONFIG_FILE_NAME);
    }

    @Test
    public void login_when_openSession() {
        session = universum.session;

        session.open();

        assertEquals(true, session.isLoggedIn());
        session.close();
    }

    @Test
    public void logout_when_closeSession() {
        session = universum.session;
        session.open();

        session.close();
        assertEquals(false, session.isLoggedIn());
    }
}