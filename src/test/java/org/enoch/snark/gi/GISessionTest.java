package org.enoch.snark.gi;

import org.enoch.snark.AppProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.enoch.snark.gi.AbstractSeleniumTest.CONFIG_DIR_NAME;
import static org.enoch.snark.gi.AbstractSeleniumTest.CONFIG_FILE_NAME;
import static org.junit.Assert.*;

public class GISessionTest {

    private GISession session;

    @Before
    public void setUp() throws Exception {
        AppProperties.loadApplicationProperties(CONFIG_DIR_NAME+CONFIG_FILE_NAME);
    }

    @Test
    public void login_when_openSession() {
        session = new GISession();

        session.open();

        assertEquals(true, session.isLoggedIn());
        session.close();
    }

    @Test
    public void logout_when_closeSession() {
        session = new GISession();
        session.open();

        session.close();
        assertEquals(false, session.isLoggedIn());
    }
}