package org.enoch.snark.gi;

import org.enoch.snark.AppProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GISessionTest {

    private GISession session;

    @Before
    public void setUp() throws Exception {
        AppProperties.loadApplicationProperties();
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